package com.news.NS.domain.dto.User;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @author 车
 * @date 2023/12/4 22 24
 * discription
 */
@Data
@ToString
public class UserUpdatePwdDTO {
    @Length(min = 11,max = 11)
    private String tel;
    @Length(min = 7, max = 20)
    private String newPwd;
    @Length(min = 6,max = 6)
    private String code;
}
