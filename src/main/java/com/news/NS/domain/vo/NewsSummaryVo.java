package com.news.NS.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * @deprecated 新闻的概要信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSummaryVo {
    private Integer newsId;
    private String title;
    private Timestamp publishTime;
    private String newsCoverUrl;//新闻封面
    private Integer publisherId;
    private String publisherUserName;
    private String publisherAvatarUrl;

}
