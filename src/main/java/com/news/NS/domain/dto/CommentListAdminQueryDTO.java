package com.news.NS.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Data
public class CommentListAdminQueryDTO {

    @Min(1)
    private Integer page;

    @Range(min = 1, max = 100)
    private Integer size;

    private Integer newsId;
}
