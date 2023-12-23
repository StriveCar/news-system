package com.news.NS.service;

import com.news.NS.common.AlertException;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Notification;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class NoticeService {
    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    UserMapper userMapper;

    public Map<String,Object> addNotice(Notification notification) {
        Integer userId=notification.getUserId();
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        if(!StringUtils.hasLength(notification.getContent()))
            throw new AlertException(ResultCode.PARAM_NOT_COMPLETE.code(),"通知内容不能为空！");
        //新发的通知，默认为未读
        notification.setHasRead(new Byte((byte) 0));

        if (notificationMapper.insert(notification) > 0){
            Map<String,Object> result=new HashMap<>();
            result.put("result","发送通知成功");
            return result;
        }else{
            throw new AlertException(500,"发送通知失败");
        }

    }

    public Map<String,Object> updateReadState(Integer notificationId){

        if (notificationMapper.count(c -> c.where(NotificationDynamicSqlSupport.notificationId, isEqualTo(notificationId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该通知不存在");

        UpdateStatementProvider updateStatementProvider=update(NotificationDynamicSqlSupport.notification)
                .set(NotificationDynamicSqlSupport.hasRead)
                .equalTo((byte) 1)
                .where(NotificationDynamicSqlSupport.notificationId,isEqualTo(notificationId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        if (notificationMapper.update(updateStatementProvider)>0){
            Map<String,Object> result=new HashMap<>();
            result.put("result","已读成功");
            return result;
        }else{
            throw new AlertException(500,"已读失败");
        }
    }

    public List<Notification> selectAllNotice(Integer userId) {

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(NotificationMapper.selectList)
                .from(NotificationDynamicSqlSupport.notification)
                .where();

        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        builder.and(NotificationDynamicSqlSupport.userId, isEqualTo(userId));

        SelectStatementProvider selectStatementProvider = builder
                .build()
                .render(RenderingStrategies.MYBATIS3);

       return  notificationMapper.selectMany(selectStatementProvider);

    }


    public List<Notification> selectIsReadNotice(Integer userId, Byte flag) {

        if(flag==null || flag!=0 && flag!=1)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "无效参数");

        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(NotificationMapper.selectList)
               .from(NotificationDynamicSqlSupport.notification)
               .where(NotificationDynamicSqlSupport.hasRead,isEqualTo(flag))
                .and(NotificationDynamicSqlSupport.userId, isEqualTo(userId));

        SelectStatementProvider selectStatementProvider = builder
               .build()
               .render(RenderingStrategies.MYBATIS3);

        return notificationMapper.selectMany(selectStatementProvider);

    }

    public Map<String,Object> deleteNotice(Integer notificationId) {

        if (notificationMapper.count(c -> c.where(NotificationDynamicSqlSupport.notificationId, isEqualTo(notificationId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该通知不存在");

        int result= notificationMapper.delete(c -> c.where(NotificationDynamicSqlSupport.notificationId, isEqualTo(notificationId)));

        if(result>0){
            Map<String,Object> map=new HashMap<>();
            map.put("result","删除成功");
            return map;
        }
        else
            throw new AlertException(500,"删除失败");
    }
}
