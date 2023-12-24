package com.news.NS.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description: 顶部的大图新闻
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigPictureNewsVo {
    private Integer newsId;
    private String title;
    private String pictureUrl;
}
