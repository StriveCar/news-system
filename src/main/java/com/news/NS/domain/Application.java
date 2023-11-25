package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Application {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_reason")
    private String applicationReason;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_status")
    private Byte applicationStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_time")
    private Date applicationTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_time")
    private Date reviewTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_remark")
    private String reviewRemark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_reason")
    public String getApplicationReason() {
        return applicationReason;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_reason")
    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason == null ? null : applicationReason.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_status")
    public Byte getApplicationStatus() {
        return applicationStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_status")
    public void setApplicationStatus(Byte applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source field: news_publisher_application.application_time")
    public Date getApplicationTime() {
        return applicationTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.application_time")
    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_time")
    public Date getReviewTime() {
        return reviewTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_time")
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_remark")
    public String getReviewRemark() {
        return reviewRemark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_remark")
    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark == null ? null : reviewRemark.trim();
    }
}