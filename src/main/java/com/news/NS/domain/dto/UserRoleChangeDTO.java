package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRoleChangeDTO {
    private Integer userId;

    private Byte identification;
}

