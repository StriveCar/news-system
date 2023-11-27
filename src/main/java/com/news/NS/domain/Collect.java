package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Collect {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.518+08:00", comments="Source field: news_collection.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.news_id")
    private Integer newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.collection_time")
    private Date collectionTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.518+08:00", comments="Source field: news_collection.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.news_id")
    public Integer getNewsId() {
        return newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.news_id")
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.collection_time")
    public Date getCollectionTime() {
        return collectionTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.collection_time")
    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}