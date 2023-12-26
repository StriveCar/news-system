package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 车
 * @date 2023/12/17 20 43
 * discription
 */
@Data
@ToString
public class ApplicationListQueryDTO {
    @Min(1)
    private Integer page;

    @Range(min = 1, max = 100)
    private Integer size;

    @NotNull
    private Byte status;
    private String reason;
    private String name;
}
