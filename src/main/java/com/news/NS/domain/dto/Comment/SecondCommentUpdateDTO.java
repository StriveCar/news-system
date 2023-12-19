package com.news.NS.domain.dto.Comment;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class SecondCommentUpdateDTO {

    @NotNull
    private Integer publisherId;

    @NotNull
    private Integer commentId;

    @Length(min = 1, max = 1000)
    private String content;
}
