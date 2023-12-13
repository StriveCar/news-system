package com.news.NS.domain.vo;


import com.news.NS.domain.User;
import lombok.Data;

import java.util.List;

/**
 * 带有一部分二级评论的一级评论
 */
@Data
public class FirstAndSecondCommentVo {

    private Integer commentId;

    private Long publishTime;

    private Integer likeNumber;

    private String content;

    private User publisher;

    private List<SecondCommentVo> secondComments;
}
