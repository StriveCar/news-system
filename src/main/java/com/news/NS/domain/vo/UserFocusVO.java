package com.news.NS.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class UserFocusVO {
    private Integer userId;
    private String username;

    private String avatarUrl;

}
