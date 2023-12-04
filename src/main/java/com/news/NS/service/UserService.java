package com.news.NS.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.UserListQueryDTO;
import com.news.NS.domain.dto.UserLoginDTO;
import com.news.NS.domain.dto.UserUpdateDTO;
import com.news.NS.domain.dto.UserUpdatePwdDTO;
import com.news.NS.mapper.UserDynamicSqlSupport;
import com.news.NS.mapper.UserMapper;
import com.news.NS.util.RedisCacheUtil;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
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
//
//    @Autowired
//    private UserRoleService userRoleService;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
    public Map<String, Object> login(UserLoginDTO userLoginDTO) {
        String pwd = SaSecureUtil.md5(userLoginDTO.getPwd());
        SelectStatementProvider queryStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.account, isEqualTo(userLoginDTO.getAcu()))
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
//            StpUtil.getRoleList();
            System.out.println(StpUtil.getRoleList());
            map.put("user", user);
            map.put("token", token);
        }
        return map;
    }

    public PageInfo<User> queryUserList(UserListQueryDTO userListQueryDTO) {
        Integer page = userListQueryDTO.getPage();
        Integer size = userListQueryDTO.getSize();
        String name = userListQueryDTO.getName();
        Byte identification = userListQueryDTO.getIdentification();
        name = StringUtils.hasLength(name) ? name + "%" : null;
        SelectStatementProvider queryStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.username, isLikeWhenPresent(name))
                .orderBy(UserDynamicSqlSupport.createTime.descending())
                .build().render(RenderingStrategies.MYBATIS3);
        Page<User> queryDataPage = PageHelper.startPage(page, size);
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setPageData(userMapper.selectMany(queryStatement));
        pageInfo.setTotalSize(queryDataPage.getTotal());
        System.out.println(userMapper.selectMany(queryStatement));
        return pageInfo;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updatePersonalInfo(UserUpdateDTO userUpdateDTO) {
        String errorMsg = "";
        String userIdContext = StpUtil.getSessionByLoginId(StpUtil.getLoginId()).getString("userId");
        if (StringUtils.hasLength(userMapper.isTelExisted(userUpdateDTO.getPhoneNumber(), userIdContext))) {
            errorMsg += userUpdateDTO.getPhoneNumber() + "手机号已被使用,";
            throw new AlertException(1000, errorMsg);
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
        int update = userMapper.updateByPrimaryKeySelective(user);
        if (update == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }
    }

    public Map<String, String> getVerifyCode(String tel) {
        // 是否已经生成验证码
        String keyCache = (String) redisCacheUtil.getCacheObject(tel);
        Map<String, String> map = new HashMap<>();
        if (StringUtils.hasLength(keyCache)) {
            // 已经生成验证码，重复获取，抛出错误
            Long expire = redisCacheUtil.getExpire(tel);
            throw new AlertException(1000, "验证码已经生成,重复获取,请" + expire + "s后重新获取");
        }
        // 验证是否有该用户，检验手机号
        SelectStatementProvider statementProvider = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.tel, isEqualTo(tel))
                .and(UserDynamicSqlSupport.state, isEqualTo(CommonConstant.STATE_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(statementProvider);
        if (users.size() != 1) {
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        // 生成验证码并返回
        // 随机四位验证码
        String code = String.valueOf((new Random().nextInt(8999) + 1000));
        redisCacheUtil.setCacheObject(tel, code, 120, TimeUnit.SECONDS);
        map.put("code", code);
        return map;
    }

    public void changeUserRole(String userId, String identification) {

    }

    public void updateUserPassword(UserUpdatePwdDTO userUpdateDTO){

    }
}
