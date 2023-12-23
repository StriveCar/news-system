package com.news.NS.domain.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/17 20 09
 * discription
 */
@Data
@ToString
public class ApplicationCreateDTO {
    private Integer userId;
    private String reason;
    private Byte status;
}
