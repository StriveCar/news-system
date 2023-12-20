package com.news.NS.domain.vo;

import com.news.NS.domain.Complaint;
import lombok.Data;

/**
 * @author 车
 * @date 2023/12/19 23 44
 * discription
 */
@Data
public class ComplaintListVo extends Complaint {
    private String complainerName;
    private String newsTitle;
    private String newsContent;
}
