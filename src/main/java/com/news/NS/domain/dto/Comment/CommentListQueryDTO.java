package com.news.NS.domain.dto.Comment;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CommentListQueryDTO {

    @Min(1)
    private Integer page;

    @Range(min = 1, max = 100)
    private Integer size;

    @NotNull
    private Integer newsId;
}
