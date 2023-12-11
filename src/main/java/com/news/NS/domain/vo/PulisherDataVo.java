package com.news.NS.domain.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/9 12 53
 * discription
 */
@Data
@ToString
public class PulisherDataVo {
    private String username;
    private Integer viewsSum;
    private Integer likeSum;
}
