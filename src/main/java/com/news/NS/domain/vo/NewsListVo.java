package com.news.NS.domain.vo;

import com.news.NS.domain.News;
import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/15 14 35
 * discription
 */
@Data
@ToString
public class NewsListVo extends News {
    private String publisherName;
    private String sectionName;

}
