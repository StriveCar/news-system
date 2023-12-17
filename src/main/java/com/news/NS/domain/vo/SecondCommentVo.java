package com.news.NS.domain.vo;


import com.news.NS.domain.User;
import lombok.Data;

@Data
public class SecondCommentVo {

    private Integer commentId;

    private String content;

    private Long publishTime;

    private Integer likeNumber;

    private Integer parentCommentId;

    private User publisher;
}
