package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/4 22 24
 * discription
 */
@Data
@ToString
public class UserUpdatePwdDTO {
    private String tel;
    private String newPwd;

    private String code;
}
