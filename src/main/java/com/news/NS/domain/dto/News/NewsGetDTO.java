package com.news.NS.domain.dto.News;

import com.news.NS.domain.News;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class NewsGetDTO {
    @NotNull
    private Integer newsId;
    @NotNull
    private Integer userId;
}
