package com.news.NS.domain.vo;

import com.news.NS.domain.Section;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 车
 * @date 2023/12/7 12 27
 * discription
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SectionNewsVo extends Section {
    private Integer viewsSum;
    private Integer newsNum;
    private Integer likeSum;
}
