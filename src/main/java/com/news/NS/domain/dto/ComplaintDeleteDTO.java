package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class ComplaintDeleteDTO {
    @NotNull
    private Integer newsId;
    @NotNull
    private Integer complaintId;
}
