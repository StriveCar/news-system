package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ComplaintModifyDTO {
    private Integer complaintId;
    private String reason;
}
