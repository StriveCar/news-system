package com.news.NS.domain.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@ToString
public class NewsCreateDTO {
    @NotNull
    private Integer sectionId;
    private String title;
    private String content;
}
