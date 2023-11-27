package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class SecondComment {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.comment_id")
    private Integer commentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.parent_comment_id")
    private Integer parentCommentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publisher_id")
    private Integer publisherId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.ike_number")
    private Integer ikeNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publish_time")
    private Date publishTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.comment_id")
    public Integer getCommentId() {
        return commentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.comment_id")
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.parent_comment_id")
    public Integer getParentCommentId() {
        return parentCommentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.parent_comment_id")
    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publisher_id")
    public Integer getPublisherId() {
        return publisherId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publisher_id")
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.ike_number")
    public Integer getIkeNumber() {
        return ikeNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.ike_number")
    public void setIkeNumber(Integer ikeNumber) {
        this.ikeNumber = ikeNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source field: second_level_comment.publish_time")
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}