package com.news.NS.domain.dto.Complaint;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ComplaintModifyDTO {
    @NotNull
    private Integer complainerId;
    @NotNull
    private Integer newsId;
    @NotNull
    private String reason;
}
