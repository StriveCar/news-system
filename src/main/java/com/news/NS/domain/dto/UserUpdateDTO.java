package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author 车
 * @date 2023/12/4 21 27
 * discription
 */


@Data
@ToString
public class UserUpdateDTO {
    @NotEmpty
    private Integer userId;

    @Length(min = 1, max = 20)
    private String account;

    @Length(min = 1, max = 16)
    private String username;

    private String avatarUrl;

    @Length(min = 11 , max = 11)
    private String phoneNumber;

}

