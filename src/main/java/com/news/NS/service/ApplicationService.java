package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Application;
import com.news.NS.domain.Complaint;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.*;
import com.news.NS.domain.vo.ApplicationListVo;
import com.news.NS.mapper.*;
import com.sun.media.sound.AiffFileReader;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

/**
 * @author 车
 * @date 2023/12/17 20 02
 * discription
 */
@Service
public class ApplicationService {

    private ApplicationMapper applicationMapper;
    private final UserMapper userMapper;
    private final NewsMapper newsMapper;

    public ApplicationService(ApplicationMapper applicationMapper,
                              NewsMapper newsMapper,
                              UserMapper userMapper) {
        this.applicationMapper = applicationMapper;
        this.newsMapper = newsMapper;
        this.userMapper = userMapper;
    }

    public void addApplication(ApplicationCreateDTO applicationCreateDTO) {
        Integer userId = applicationCreateDTO.getUserId();
        String reason = applicationCreateDTO.getReason();

        if (userId == null || !StringUtils.hasLength(reason)) {
            throw new AlertException(ResultCode.PARAM_NOT_COMPLETE);
        }

        //检查用户是否存在。
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) != 1) {
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        Application application = new Application();
        application.setUserId(userId);
        application.setApplicationReason(reason);
        applicationMapper.insert(application);
    }

    public void delApplication(Integer userId) {
        if (applicationMapper.deleteByPrimaryKey(userId) == 0) {
            throw new AlertException(ResultCode.DELETE_ERROR);
        }
    }

    public void updateApplication(ApplicationCreateDTO applicationCreateDTO) {
        Integer userId = applicationCreateDTO.getUserId();
        String reason = applicationCreateDTO.getReason();

        if (userId == null || !StringUtils.hasLength(reason)) {
            throw new AlertException(ResultCode.PARAM_NOT_COMPLETE);
        }

        //检查用户是否存在。
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) != 1) {
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        Application application = new Application();
        application.setUserId(userId);
        application.setApplicationReason(reason);
        application.setApplicationStatus(CommonConstant.APPLICATION_NOTPASS);
        if (applicationMapper.updateByPrimaryKey(application) != 1) {
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    public PageInfo<ApplicationListVo> queryApplicationList(ApplicationListQueryDTO applicationListQueryDTO) {
        Integer page = applicationListQueryDTO.getPage();
        Integer size = applicationListQueryDTO.getSize();
        String name = applicationListQueryDTO.getName();
        Byte status = applicationListQueryDTO.getStatus();
        String reason = applicationListQueryDTO.getReason();
        final String finalName = StringUtils.hasLength(name) ? name + "%" : null;
        reason = StringUtils.hasLength(reason) ? reason + "%" : null;

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = select(ApplicationMapper.selectList)
                .from(ApplicationDynamicSqlSupport.application)
                .where(ApplicationDynamicSqlSupport.applicationReason, isLikeWhenPresent(reason));

        if (status != null) {
            where.and(ApplicationDynamicSqlSupport.applicationStatus, isEqualTo(status));
        }

        SelectStatementProvider queryStatement = where
                .orderBy(ApplicationDynamicSqlSupport.applicationTime)
                .build().render(RenderingStrategies.MYBATIS3);

        Page<Application> queryDataPage = PageHelper.startPage(page, size);
        List<Application> applicationList = applicationMapper.selectMany(queryStatement);

        BeanCopier applicationCopier = BeanCopier.create(Application.class, ApplicationListVo.class, false);

        List<ApplicationListVo> applicationListVos = applicationList.stream().map(item -> {
            ApplicationListVo applicationListVo = new ApplicationListVo();
            applicationCopier.copy(item, applicationListVo, null);
            Optional<User> optionalUser = userMapper.selectOne(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(item.getUserId()))
                    .and(UserDynamicSqlSupport.username, isLikeWhenPresent(finalName)));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                applicationListVo.setUsername(user.getUsername());
                applicationListVo.setAccount(user.getAccount());
                return applicationListVo;
            } else {
                return null;
            }

        }).filter(Objects::nonNull).collect(Collectors.toList());
        PageInfo<ApplicationListVo> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setPageData(applicationListVos);
        pageInfo.setTotalSize(queryDataPage.getTotal());
        return pageInfo;
    }

    public Map<String,Object> queryMyApplication(Integer userId){
        if (userId==null){
            throw new AlertException(ResultCode.PARAM_IS_INVALID);
        }
        Map<String,Object> map = new HashMap<>();
        Optional<Application> applicationOptional = applicationMapper.selectByPrimaryKey(userId);
        if (applicationOptional.isPresent()){
            map.put("application",applicationOptional.get());
        }else {
            map.put("msg","该用户未申请");
        }
        return map;
    }

    public void passApplication(Integer userId, boolean pass, String remark) {
        if (!StringUtils.hasLength(remark)) {
            remark = "";
        }
        Optional<Application> applicationOptional = applicationMapper.selectByPrimaryKey(userId);
        Optional<User> userOptional = userMapper.selectByPrimaryKey(userId);
        if (userOptional.isPresent() && applicationOptional.isPresent()) {
            Application application=applicationOptional.get();
            if (pass){
                application.setApplicationStatus(CommonConstant.APPLICATION_PASS);
            }else{
                application.setApplicationStatus(CommonConstant.APPLICATION_REFUSE);
            }
            application.setReviewRemark(remark);
            if (applicationMapper.updateByPrimaryKey(application) ==0){
                throw new AlertException(ResultCode.SYSTEM_ERROR);
            }
        }else{
            throw new AlertException(ResultCode.PARAM_IS_INVALID);
        }
    }
}
