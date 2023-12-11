package com.news.NS.domain.dto;
import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/10 22 33
 * discription
 */
@Data
@ToString
public class UserRegisterDTO {
    private String tel;

    private String name;

    private String act;

    private String pwd;

    private String code;
}
