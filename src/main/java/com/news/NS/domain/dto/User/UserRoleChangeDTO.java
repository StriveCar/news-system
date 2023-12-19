package com.news.NS.domain.dto.User;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class UserRoleChangeDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Byte identification;
}

