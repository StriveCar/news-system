package com.news.NS.domain.dto.Complaint;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ComplaintModifyDTO {
    @NotNull
    private Integer complaintId;
    @NotNull
    private String reason;
}
