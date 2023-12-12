package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginDTO {
    private String act;
    private String pwd;

}
