package com.news.NS.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FirstCommentDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1045344+08:00", comments="Source Table: first_level_comment")
    public static final FirstComment firstComment = new FirstComment();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.comment_id")
    public static final SqlColumn<Integer> commentId = firstComment.commentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.news_id")
    public static final SqlColumn<Integer> newsId = firstComment.newsId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.publisher_id")
    public static final SqlColumn<Integer> publisherId = firstComment.publisherId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.like_number")
    public static final SqlColumn<Integer> likeNumber = firstComment.likeNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.content")
    public static final SqlColumn<String> content = firstComment.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source field: first_level_comment.publish_time")
    public static final SqlColumn<Date> publishTime = firstComment.publishTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-12T11:49:08.1055317+08:00", comments="Source Table: first_level_comment")
    public static final class FirstComment extends SqlTable {
        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> newsId = column("news_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> publisherId = column("publisher_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> likeNumber = column("like_number", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Date> publishTime = column("publish_time", JDBCType.TIMESTAMP);

        public FirstComment() {
            super("first_level_comment");
        }
    }
}