package com.news.NS.domain.dto.User;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 车
 * @date 2023/12/10 22 33
 * discription
 */
@Data
@ToString
public class UserRegisterDTO {
    @Length(min = 11,max = 11)
    private String tel;

    @Length(min = 1, max = 16)
    private String name;

    @Length(min = 6, max = 20)
    private String act;

    @NotNull
    private String pwd;

    @Length(min = 6,max = 6)
    private String code;
}
