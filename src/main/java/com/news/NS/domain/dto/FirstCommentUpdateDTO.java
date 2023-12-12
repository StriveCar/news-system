package com.news.NS.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class FirstCommentUpdateDTO {
    @NotNull(message = "newsId required")
    private Integer newsId;

    @NotNull(message = "publisherId required")
    private Integer publisherId;

    @Length(min = 1, max = 1000, message = "content must be between 1 and 1000")
    private String content;
}
