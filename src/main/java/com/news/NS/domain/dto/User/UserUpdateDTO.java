package com.news.NS.domain.dto.User;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 车
 * @date 2023/12/4 21 27
 * discription
 */


@Data
@ToString
public class UserUpdateDTO {
    @NotNull
    private Integer userId;

    @Length(min = 6, max = 20)
    private String account;

    @Length(min = 1, max = 16)
    private String username;

    private String avatarUrl;

    @Length(min = 11 , max = 11)
    private String phoneNumber;

}

