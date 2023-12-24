package com.news.NS.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.User.*;
import com.news.NS.mapper.UserDynamicSqlSupport;
import com.news.NS.mapper.UserMapper;
import com.news.NS.util.CommonUtils;
import com.news.NS.util.RedisCacheUtil;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private RedisCacheUtil<String> redisCacheUtil;

    public Map<String, Object> login(UserLoginDTO userLoginDTO) {
        String pwd = SaSecureUtil.md5(userLoginDTO.getPwd());
        SelectStatementProvider queryStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.account, isEqualTo(userLoginDTO.getAct()))
                .and(UserDynamicSqlSupport.password, isEqualTo(pwd))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<User> userOptional = userMapper.selectOne(queryStatement);
        User user;
        Map<String, Object> map = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            map = new HashMap<>();
            StpUtil.login(user.getUserId());
            String token = StpUtil.getTokenValueByLoginId(StpUtil.getLoginId());
            StpUtil.getSessionByLoginId(StpUtil.getLoginId()).set("userId", user.getUserId());
            map.put("user", user);
            map.put("token", token);
        }
        return map;
    }

    public Map<String, Object> register(UserRegisterDTO userRegisterDTO) {
        SelectStatementProvider statement = select(count())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.account, isEqualTo(userRegisterDTO.getAct()))
                .build().render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider telStatement = select(count())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phoneNumber, isEqualTo(userRegisterDTO.getTel()))
                .build().render(RenderingStrategies.MYBATIS3);
        if (userMapper.count(statement) > 0) {
            throw new AlertException(1000, "账号" + userRegisterDTO.getAct() + "已被注册");
        }
        if (userMapper.count(telStatement) > 0) {
            throw new AlertException(1000, "手机号" + userRegisterDTO.getTel() + "已被注册");
        }

        String code = redisCacheUtil.getCacheObject(userRegisterDTO.getTel());
        if (!StringUtils.hasLength(code)) {
            throw new AlertException(1000, "请重新获取验证码");
        }
        if (!code.equals(userRegisterDTO.getCode())) {
            throw new AlertException(1000, "验证码错误");
        }
        User user = new User();
        user.setPhoneNumber(userRegisterDTO.getTel());
        user.setUsername(userRegisterDTO.getName());
        user.setAccount(userRegisterDTO.getAct());
        user.setPassword(SaSecureUtil.md5(userRegisterDTO.getPwd()));
        userMapper.insertSelective(user);
        // 返回信息
        Map<String, Object> map = new HashMap<>();
        SelectStatementProvider userIdStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phoneNumber, isEqualTo(userRegisterDTO.getTel()))
                .and(UserDynamicSqlSupport.account,isEqualTo(userRegisterDTO.getAct()))
                .build().render(RenderingStrategies.MYBATIS3);
        // 用户登录
        user.setUserId(userMapper.selectOne(userIdStatement).get().getUserId());
        StpUtil.login(user.getUserId());
        // 获取登录token
        String token = StpUtil.getTokenValueByLoginId(StpUtil.getLoginId());
        // 后台存储用户信息
        StpUtil.getSessionByLoginId(StpUtil.getLoginId()).set("userId", user.getUserId());
        // 返回结果
        map.put("user", user);
        map.put("token", token);
        return map;
    }

    public Map<String, Object> adminLogin(UserLoginDTO userLoginDTO) {
        String pwd = SaSecureUtil.md5(userLoginDTO.getPwd());
        SelectStatementProvider queryStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.account, isEqualTo(userLoginDTO.getAct()))
                .or(UserDynamicSqlSupport.phoneNumber,isEqualTo(userLoginDTO.getAct()))
                .and(UserDynamicSqlSupport.password, isEqualTo(pwd))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<User> userOptional = userMapper.selectOne(queryStatement);
        User user;
        Map<String, Object> map;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            map = new HashMap<>();
            // 用户登录
            StpUtil.login(user.getUserId());
            // 获取登录token
            String token = StpUtil.getTokenValueByLoginId(StpUtil.getLoginId());
            // 后台存储用户信息
            StpUtil.getSessionByLoginId(StpUtil.getLoginId()).set("userId", user.getUserId());
            // 返回结果
            user.setPassword(null);
            map.put("user", user);
            map.put("token", token);
            Byte identification = user.getIdentification();
            if (!identification.equals(CommonConstant.ADMIN_ROLE)
                    && !identification.equals(CommonConstant.SUPER_ADMIN_ROLE)) {
                // 权限不足，踢出下线，抛出错误
                StpUtil.logout();
                throw new AlertException(1000, "您不是管理员，没有权限登录后台管理");
            }
        } else {
            throw new AlertException(1000, "账号或者密码错误");
        }
        return map;
    }

    public PageInfo<User> queryUserList(UserListQueryDTO userListQueryDTO) {
        Integer page = userListQueryDTO.getPage();
        Integer size = userListQueryDTO.getSize();
        String name = userListQueryDTO.getName();
        Byte identification = userListQueryDTO.getIdentification();
        name = StringUtils.hasLength(name) ? name + "%" : null;
        System.out.println(name);
        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder queryStatementBuilder = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.username, isLikeWhenPresent(name));

        if (identification != null) {
            queryStatementBuilder.and(UserDynamicSqlSupport.identification, isEqualTo(identification));
        }
        SelectStatementProvider queryStatement = queryStatementBuilder
                .orderBy(UserDynamicSqlSupport.createTime.descending())
                .build().render(RenderingStrategies.MYBATIS3);

        Page<User> queryDataPage = PageHelper.startPage(page, size);
        List<User> userList = userMapper.selectMany(queryStatement);
        userList.forEach(user -> {
            user.setPassword(null);
            String tel = CommonUtils.encodeTel(user.getPhoneNumber());
            user.setPhoneNumber(tel);
        });
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setPageData(userList);
        pageInfo.setTotalSize(queryDataPage.getTotal());
        return pageInfo;
    }

    public User getUserinfo(Integer userId) {
        SelectStatementProvider statementProvider = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.userId, isEqualTo(userId))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<User> users = userMapper.selectOne(statementProvider);
        if (users.isPresent()) {
            User user = users.get();
            user.setPassword(null);
            return user;
        } else {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserInfo(UserUpdateDTO userUpdateDTO) {
        String errorMsg = "";
        String userIdContext = StpUtil.getSessionByLoginId(StpUtil.getLoginId()).getString("userId");
        if (StringUtils.hasLength(userMapper.isTelExisted(userUpdateDTO.getPhoneNumber(), userIdContext))) {
            errorMsg += userUpdateDTO.getPhoneNumber() + "手机号已被使用,";
            throw new AlertException(1000, errorMsg);
        }
        if (userMapper.count(c->c.where(UserDynamicSqlSupport.account, isEqualTo(userUpdateDTO.getAccount()))) > 0) {
            throw new AlertException(1000, "账号" + userUpdateDTO.getAccount() + "已被注册");
        }
        // 获取用户24小时可更新个人信息次数
        Integer leftUpdateTimes = redisCacheUtil.getCacheObject(userUpdateDTO.getUserId() + "updateInfo");
        if (leftUpdateTimes != null) {
            throw new AlertException(ResultCode.UPDATE_USERINFO_OUT_OF_TIMES);
        } else {
            redisCacheUtil.setCacheObject(userUpdateDTO.getUserId() + "updateInfo", 0, 24, TimeUnit.HOURS);
        }
        // 更新用户信息
        User user = new User();
        user.setUserId(userUpdateDTO.getUserId());
        user.setAccount(userUpdateDTO.getAccount());
        user.setAvatarUrl(userUpdateDTO.getAvatarUrl());
        user.setUsername(userUpdateDTO.getUsername());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setLastUpdateTime(new Date(System.currentTimeMillis()));
        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }
    }

    public void getVerifyCode(String tel) {
        // 是否已经生成验证码
        String keyCache = redisCacheUtil.getCacheObject(tel);
        if (StringUtils.hasLength(keyCache)) {
            // 已经生成验证码，重复获取，抛出错误
            Long expire = redisCacheUtil.getExpire(tel);
            throw new AlertException(1000, "验证码已经生成,重复获取,请" + expire + "s后重新获取");
        }
        // 验证是否有该用户，检验手机号
        SelectStatementProvider statementProvider = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phoneNumber, isEqualTo(tel))
                .build().render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(statementProvider);
        if (users.size() != 0) {
            throw new AlertException(ResultCode.TEL_EXIST);
        }
        // 生成验证码并返回
        // 随机六位验证码
        String code = String.valueOf((new Random().nextInt(899999) + 100000));
        CommonUtils.send(tel, code);
        redisCacheUtil.setCacheObject(tel, code, 120, TimeUnit.SECONDS);
    }

    public void changeUserRole(Integer userId, Byte identification) {
        if (userId == null) {
            throw new AlertException(1000, "用户ID不能为空");
        }
        Integer userIdContext = StpUtil.getSessionByLoginId(StpUtil.getLoginId()).getInt("userId");
        if (userIdContext.equals(userId)) {
            throw new AlertException(ResultCode.OPERATE_OBJECT_NOT_SELF);
        }
        Byte adminrole = Byte.valueOf(StpUtil.getRoleList().get(0));
        if (adminrole.intValue() <= identification.intValue()){
            throw new AlertException(ResultCode.OPERATE_OBJECT_NOT_SELF);
        }
        Optional<User> optionalUser = userMapper.selectByPrimaryKey(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!Objects.equals(user.getIdentification(), identification)) {
                user.setIdentification(identification);
                UpdateStatementProvider update = update(UserDynamicSqlSupport.user)
                        .set(UserDynamicSqlSupport.identification)
                        .equalTo(user.getIdentification())
                        .where(UserDynamicSqlSupport.userId, isEqualTo(userId))
                        .build().render(RenderingStrategies.MYBATIS3);
                userMapper.update(update);
            }
        } else {
            throw new AlertException(1000, "该用户不存在");
        }
    }

    public void updateUserPassword(UserUpdatePwdDTO userUpdatePwdDTO) {
        String newPwd = userUpdatePwdDTO.getNewPwd();
        String code = redisCacheUtil.getCacheObject(userUpdatePwdDTO.getTel());
        if (!StringUtils.hasLength(code)) {
            throw new AlertException(1000, "请重新获取验证码");
        }
        if (!code.equals(userUpdatePwdDTO.getCode())) {
            throw new AlertException(1000, "验证码错误");
        }
        SelectStatementProvider statementProvider = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phoneNumber, isEqualTo(userUpdatePwdDTO.getTel()))
                .build().render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(statementProvider);
        if (users.size() != 1) {
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        // 清除存储的code
        redisCacheUtil.deleteObject(userUpdatePwdDTO.getTel());

        // 修改密码
        User user = users.get(0);
        user.setPassword(SaSecureUtil.md5(newPwd));
        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }
    }
}
