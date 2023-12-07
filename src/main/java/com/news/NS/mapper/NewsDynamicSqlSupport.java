package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NewsDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.25+08:00", comments="Source Table: news")
    public static final News news = new News();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.news_id")
    public static final SqlColumn<Integer> newsId = news.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.publisher_id")
    public static final SqlColumn<Integer> publisherId = news.publisherId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.section_id")
    public static final SqlColumn<Integer> sectionId = news.sectionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.title")
    public static final SqlColumn<String> title = news.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.content")
    public static final SqlColumn<String> content = news.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.news_views")
    public static final SqlColumn<Integer> newsViews = news.newsViews;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.publish_time")
    public static final SqlColumn<Date> publishTime = news.publishTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.publish_status")
    public static final SqlColumn<Byte> publishStatus = news.publishStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source field: news.like_number")
    public static final SqlColumn<Integer> likeNumber = news.likeNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-07T12:51:14.251+08:00", comments="Source Table: news")
    public static final class News extends SqlTable {
        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> publisherId = column("publisher_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> sectionId = column("section_id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> newsViews = column("news_views", JDBCType.INTEGER);

        public final SqlColumn<Date> publishTime = column("publish_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Byte> publishStatus = column("publish_status", JDBCType.TINYINT);

        public final SqlColumn<Integer> likeNumber = column("like_number", JDBCType.INTEGER);

        public News() {
            super("news");
        }
    }
}