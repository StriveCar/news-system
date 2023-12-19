package com.news.NS.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectNewsOv {
private Integer newsId;
private String title;
private String sectionName;
private String content;
private String publisherName;
private String publisherAvatarUrl;
}
