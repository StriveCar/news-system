package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class User {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.account")
    private String account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.avatar_url")
    private String avatarUrl;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.phone_number")
    private String phoneNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.identification")
    private Byte identification;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.create_time")
    private Date createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.last_update_time")
    private Date lastUpdateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.account")
    public String getAccount() {
        return account;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.account")
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source field: user_info.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.identification")
    public Byte getIdentification() {
        return identification;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.identification")
    public void setIdentification(Byte identification) {
        this.identification = identification;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.create_time")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.create_time")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.532+08:00", comments="Source field: user_info.last_update_time")
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identification=" + identification +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}