package com.news.NS.domain.dto.News;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@ToString
public class NewsCreateDTO {
    @NotNull
    private Integer publisherId;
    @NotNull
    private Integer sectionId;
    @NotNull
    private String title;
    @NotNull
    private String content;
}
