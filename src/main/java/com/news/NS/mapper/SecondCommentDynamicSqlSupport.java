package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SecondCommentDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source Table: second_level_comment")
    public static final SecondComment secondComment = new SecondComment();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.comment_id")
    public static final SqlColumn<Integer> commentId = secondComment.commentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.parent_comment_id")
    public static final SqlColumn<Integer> parentCommentId = secondComment.parentCommentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.publisher_id")
    public static final SqlColumn<Integer> publisherId = secondComment.publisherId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.ike_number")
    public static final SqlColumn<Integer> ikeNumber = secondComment.ikeNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.content")
    public static final SqlColumn<String> content = secondComment.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source field: second_level_comment.publish_time")
    public static final SqlColumn<Date> publishTime = secondComment.publishTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9832758+08:00", comments="Source Table: second_level_comment")
    public static final class SecondComment extends SqlTable {
        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> parentCommentId = column("parent_comment_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> publisherId = column("publisher_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> ikeNumber = column("ike_number", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Date> publishTime = column("publish_time", JDBCType.TIMESTAMP);

        public SecondComment() {
            super("second_level_comment");
        }
    }
}