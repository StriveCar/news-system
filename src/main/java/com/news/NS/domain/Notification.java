package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Notification {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.494+08:00", comments="Source field: notification.notification_id")
    private Integer notificationId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.create_time")
    private Date createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.has_read")
    private Byte hasRead;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.type")
    private Byte type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.496+08:00", comments="Source field: notification.notification_id")
    public Integer getNotificationId() {
        return notificationId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.notification_id")
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.create_time")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.create_time")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.has_read")
    public Byte getHasRead() {
        return hasRead;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.has_read")
    public void setHasRead(Byte hasRead) {
        this.hasRead = hasRead;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.497+08:00", comments="Source field: notification.type")
    public Byte getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.498+08:00", comments="Source field: notification.type")
    public void setType(Byte type) {
        this.type = type;
    }
}