package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.Section;
import com.news.NS.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.util.List;

@Controller
@ResponseBodyResult
@Api(tags = "栏目管理")
@Validated
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/section/get/list")
    @ApiOperation(value = "管理员查询系统配置列表")
    public List<Section> querySysConfigListApi() {
        return sectionService.querySectionList(1,10);
    }
}
