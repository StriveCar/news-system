package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@ToString
public class ComplaintCreateDTO {
    @NotNull
    private Integer newsId;
    @NotNull
    private Integer complainerId;
    @NotNull
    private String reason;
}
