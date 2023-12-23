package com.news.NS.domain.vo;

import com.news.NS.domain.Application;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 车
 * @date 2023/12/17 20 54
 * discription
 */
@Data
@ToString
public class ApplicationListVo extends Application {
    private String username;
    private String account;
}
