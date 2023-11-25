package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NewsReviewDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source Table: news_review")
    public static final NewsReview newsReview = new NewsReview();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.reviewer_id")
    public static final SqlColumn<Integer> reviewerId = newsReview.reviewerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source field: news_review.news_id")
    public static final SqlColumn<Integer> newsId = newsReview.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source field: news_review.review_status")
    public static final SqlColumn<Byte> reviewStatus = newsReview.reviewStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source field: news_review.review_time")
    public static final SqlColumn<Date> reviewTime = newsReview.reviewTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source field: news_review.remark")
    public static final SqlColumn<String> remark = newsReview.remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9782927+08:00", comments="Source Table: news_review")
    public static final class NewsReview extends SqlTable {
        public final SqlColumn<Integer> reviewerId = column("reviewer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Byte> reviewStatus = column("review_status", JDBCType.TINYINT);

        public final SqlColumn<Date> reviewTime = column("review_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public NewsReview() {
            super("news_review");
        }
    }
}