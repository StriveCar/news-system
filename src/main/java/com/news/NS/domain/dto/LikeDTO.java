package com.news.NS.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LikeDTO {
    @NotNull
    private Integer commentId;
    @NotNull
    private Integer userId;
}
