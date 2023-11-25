package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class NewsReview {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source field: news_review.reviewer_id")
    private Integer reviewerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.news_id")
    private Integer newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_status")
    private Byte reviewStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_time")
    private Date reviewTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.remark")
    private String remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.reviewer_id")
    public Integer getReviewerId() {
        return reviewerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.reviewer_id")
    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.news_id")
    public Integer getNewsId() {
        return newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.news_id")
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_status")
    public Byte getReviewStatus() {
        return reviewStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_status")
    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_time")
    public Date getReviewTime() {
        return reviewTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.review_time")
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.remark")
    public String getRemark() {
        return remark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.remark")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}