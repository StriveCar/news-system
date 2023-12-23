package com.news.NS.domain.dto.News;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class NewsSearchParamDTO<T> {
    /*
     * 根据参数 param 查询符合条件的新闻
     * param可以是 sectionId、publisherId、publishStatus等
     * 类型可以是Integer、Byte、String等类型
     */
    @NotNull
    private T param;
    @Min(1)
    private Integer page;
    @Range(min = 1, max = 100)
    private Integer size;
}
