package com.news.NS.domain.vo;

import com.news.NS.domain.User;
import lombok.Data;

/**
 * 管理端的评论vo
 * */
@Data
public class CommentAdminVo {

    private Integer commentId;

    private Long publishTime;

    private String content;

    private String newsTitle;

    private String newsContent;

    private Integer likeNumber;

    private Integer parentCommentId;

    private String parentCommentContent;

    private User publisher;
}
