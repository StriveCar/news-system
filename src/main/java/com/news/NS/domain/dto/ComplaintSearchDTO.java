package com.news.NS.domain.dto;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ComplaintSearchDTO<T> {
    private T searchKeyword;
    private Integer page;
    private Integer size;
}
