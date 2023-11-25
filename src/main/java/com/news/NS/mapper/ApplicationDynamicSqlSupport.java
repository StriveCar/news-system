package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ApplicationDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source Table: news_publisher_application")
    public static final Application application = new Application();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.user_id")
    public static final SqlColumn<Integer> userId = application.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.application_reason")
    public static final SqlColumn<String> applicationReason = application.applicationReason;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.application_status")
    public static final SqlColumn<Byte> applicationStatus = application.applicationStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.application_time")
    public static final SqlColumn<Date> applicationTime = application.applicationTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_time")
    public static final SqlColumn<Date> reviewTime = application.reviewTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source field: news_publisher_application.review_remark")
    public static final SqlColumn<String> reviewRemark = application.reviewRemark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source Table: news_publisher_application")
    public static final class Application extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<String> applicationReason = column("application_reason", JDBCType.VARCHAR);

        public final SqlColumn<Byte> applicationStatus = column("application_status", JDBCType.TINYINT);

        public final SqlColumn<Date> applicationTime = column("application_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> reviewTime = column("review_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> reviewRemark = column("review_remark", JDBCType.VARCHAR);

        public Application() {
            super("news_publisher_application");
        }
    }
}