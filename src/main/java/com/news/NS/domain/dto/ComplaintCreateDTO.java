package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class ComplaintCreateDTO {
    private Integer newsId;
    private Integer complainerId;
    private String reason;
}
