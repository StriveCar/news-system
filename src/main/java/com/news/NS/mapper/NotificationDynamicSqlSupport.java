package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NotificationDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.5+08:00", comments="Source Table: notification")
    public static final Notification notification = new Notification();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.5+08:00", comments="Source field: notification.notification_id")
    public static final SqlColumn<Integer> notificationId = notification.notificationId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.501+08:00", comments="Source field: notification.user_id")
    public static final SqlColumn<Integer> userId = notification.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.501+08:00", comments="Source field: notification.content")
    public static final SqlColumn<String> content = notification.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.501+08:00", comments="Source field: notification.create_time")
    public static final SqlColumn<Date> createTime = notification.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.501+08:00", comments="Source field: notification.has_read")
    public static final SqlColumn<Byte> hasRead = notification.hasRead;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.501+08:00", comments="Source field: notification.type")
    public static final SqlColumn<Byte> type = notification.type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.5+08:00", comments="Source Table: notification")
    public static final class Notification extends SqlTable {
        public final SqlColumn<Integer> notificationId = column("notification_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Byte> hasRead = column("has_read", JDBCType.TINYINT);

        public final SqlColumn<Byte> type = column("type", JDBCType.TINYINT);

        public Notification() {
            super("notification");
        }
    }
}