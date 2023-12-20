package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;

/**
 * @author 车
 * @date 2023/12/19 23 52
 * discription

 */
@Data
@ToString
public class ComplaintListDTO {

    private String title;
    private String reason;
    private String name;

    @Min(1)
    private Integer page;
    @Range(min = 1, max = 100)
    private Integer size;

}
