package com.news.NS.domain.dto.Complaint;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ComplaintSearchDTO<T> {
    @NotNull
    private T searchKeyword;
    @Min(1)
    private Integer page;
    @Range(min = 1, max = 100)
    private Integer size;
}
