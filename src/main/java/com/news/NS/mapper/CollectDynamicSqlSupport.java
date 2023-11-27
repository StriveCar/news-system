package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CollectDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source Table: news_collection")
    public static final Collect collect = new Collect();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.user_id")
    public static final SqlColumn<Integer> userId = collect.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.news_id")
    public static final SqlColumn<Integer> newsId = collect.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source field: news_collection.collection_time")
    public static final SqlColumn<Date> collectionTime = collect.collectionTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.519+08:00", comments="Source Table: news_collection")
    public static final class Collect extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Date> collectionTime = column("collection_time", JDBCType.TIMESTAMP);

        public Collect() {
            super("news_collection");
        }
    }
}