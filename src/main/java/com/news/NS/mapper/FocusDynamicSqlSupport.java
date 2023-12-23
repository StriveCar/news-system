package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FocusDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2410619+08:00", comments="Source Table: user_focus")
    public static final Focus focus = new Focus();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2410619+08:00", comments="Source field: user_focus.user_id")
    public static final SqlColumn<Integer> userId = focus.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2420592+08:00", comments="Source field: user_focus.focused_user_id")
    public static final SqlColumn<Integer> focusedUserId = focus.focusedUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2420592+08:00", comments="Source field: user_focus.focus_time")
    public static final SqlColumn<Date> focusTime = focus.focusTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2410619+08:00", comments="Source Table: user_focus")
    public static final class Focus extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> focusedUserId = column("focused_user_id", JDBCType.INTEGER);

        public final SqlColumn<Date> focusTime = column("focus_time", JDBCType.TIMESTAMP);

        public Focus() {
            super("user_focus");
        }
    }
}