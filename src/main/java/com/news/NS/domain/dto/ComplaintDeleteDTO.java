package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ComplaintDeleteDTO {
    private Integer newsId;
    private Integer complaintId;
}
