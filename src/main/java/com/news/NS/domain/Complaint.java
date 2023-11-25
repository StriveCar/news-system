package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Complaint {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9683445+08:00", comments="Source field: news_complaint.complainer_id")
    private Integer complainerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.news_id")
    private Integer newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_time")
    private Date complaintTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_reason")
    private String complaintReason;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9683445+08:00", comments="Source field: news_complaint.complainer_id")
    public Integer getComplainerId() {
        return complainerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complainer_id")
    public void setComplainerId(Integer complainerId) {
        this.complainerId = complainerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.news_id")
    public Integer getNewsId() {
        return newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.news_id")
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_time")
    public Date getComplaintTime() {
        return complaintTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_time")
    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_reason")
    public String getComplaintReason() {
        return complaintReason;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9693147+08:00", comments="Source field: news_complaint.complaint_reason")
    public void setComplaintReason(String complaintReason) {
        this.complaintReason = complaintReason == null ? null : complaintReason.trim();
    }
}