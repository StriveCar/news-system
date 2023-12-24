package com.news.NS.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class FocusVo {
    private Integer userId;
    private String username;
    private String avatarUrl;
}
