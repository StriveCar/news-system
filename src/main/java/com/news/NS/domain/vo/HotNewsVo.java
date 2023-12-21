package com.news.NS.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:首页顶部大图右侧的新闻，以及下面部分的 "要闻聚焦" 等只展示新闻标题的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotNewsVo {
    private Integer newsId;
    private String Title;
}
