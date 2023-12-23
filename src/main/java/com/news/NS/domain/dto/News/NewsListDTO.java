package com.news.NS.domain.dto.News;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class NewsListDTO {
    private Integer sectionId;
    private String content;
    private String title;
    @NotNull
    private Byte status;
    @Min(1)
    private Integer page;
    @Range(min = 1, max = 100)
    private Integer size;
}
