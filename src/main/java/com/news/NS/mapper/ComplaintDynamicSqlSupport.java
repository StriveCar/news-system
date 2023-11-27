package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ComplaintDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source Table: news_complaint")
    public static final Complaint complaint = new Complaint();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source field: news_complaint.complainer_id")
    public static final SqlColumn<Integer> complainerId = complaint.complainerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source field: news_complaint.news_id")
    public static final SqlColumn<Integer> newsId = complaint.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source field: news_complaint.complaint_time")
    public static final SqlColumn<Date> complaintTime = complaint.complaintTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source field: news_complaint.complaint_reason")
    public static final SqlColumn<String> complaintReason = complaint.complaintReason;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.514+08:00", comments="Source Table: news_complaint")
    public static final class Complaint extends SqlTable {
        public final SqlColumn<Integer> complainerId = column("complainer_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Date> complaintTime = column("complaint_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> complaintReason = column("complaint_reason", JDBCType.VARCHAR);

        public Complaint() {
            super("news_complaint");
        }
    }
}