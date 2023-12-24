package com.news.NS.domain.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/24 16 45
 * discription
 */
@Data
@ToString
public class NewsStatusVo {
    private Byte publishStatus;
    private Integer count;
}
