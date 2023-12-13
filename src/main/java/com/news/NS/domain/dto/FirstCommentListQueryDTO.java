package com.news.NS.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * 一级评论查询
 * */
@Data
public class FirstCommentListQueryDTO {

    @Min(value = 1, message = "page must be greater than 0")
    private Integer page;

    @Range(min = 1, max = 100, message = "size must be between 1 and 100")
    private Integer size;

    private Integer newsId;

    private Integer publisherId;

    private Long publishTime;
}
