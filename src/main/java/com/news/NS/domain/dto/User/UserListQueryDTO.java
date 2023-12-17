package com.news.NS.domain.dto.User;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @author 车
 * @date 2023/12/4 19 47
 * discription
 */
@Data
@ToString
public class UserListQueryDTO {
    @Min(1)
    private Integer page;

    @Range(min = 1, max = 100)
    private Integer size;

    private String name;

    private Byte identification;
}

