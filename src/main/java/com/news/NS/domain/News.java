package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class News {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_id")
    private Integer newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publisher_id")
    private Integer publisherId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.section_id")
    private Integer sectionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_views")
    private Integer newsViews;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_time")
    private Date publishTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_status")
    private Byte publishStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_id")
    public Integer getNewsId() {
        return newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_id")
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publisher_id")
    public Integer getPublisherId() {
        return publisherId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publisher_id")
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.section_id")
    public Integer getSectionId() {
        return sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.section_id")
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_views")
    public Integer getNewsViews() {
        return newsViews;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.news_views")
    public void setNewsViews(Integer newsViews) {
        this.newsViews = newsViews;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_time")
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_status")
    public Byte getPublishStatus() {
        return publishStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.529+08:00", comments="Source field: news.publish_status")
    public void setPublishStatus(Byte publishStatus) {
        this.publishStatus = publishStatus;
    }
}