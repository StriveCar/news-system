package com.news.NS.domain.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.ParameterizedType;
import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePictureNewsVo {
    private Integer newsId;
    private String title;

    private String pictureUrl;
}
