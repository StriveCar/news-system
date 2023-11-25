package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CollectionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9752966+08:00", comments="Source Table: news_collection")
    public static final Collection collection = new Collection();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9752966+08:00", comments="Source field: news_collection.user_id")
    public static final SqlColumn<Integer> userId = collection.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9752966+08:00", comments="Source field: news_collection.news_id")
    public static final SqlColumn<Integer> newsId = collection.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source field: news_collection.collection_time")
    public static final SqlColumn<Date> collectionTime = collection.collectionTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9752966+08:00", comments="Source Table: news_collection")
    public static final class Collection extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Date> collectionTime = column("collection_time", JDBCType.TIMESTAMP);

        public Collection() {
            super("news_collection");
        }
    }
}