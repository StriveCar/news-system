package com.news.NS.domain.dto.User;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class UserLoginDTO {
    @NotNull
    private String act;
    @NotNull
    private String pwd;

}
