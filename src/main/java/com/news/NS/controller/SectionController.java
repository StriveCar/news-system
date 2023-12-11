package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.Section;
import com.news.NS.domain.vo.SectionNewsVo;
import com.news.NS.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@ResponseBodyResult
@Api(tags = "栏目管理")
@Validated
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/section/get/list")
    @ApiOperation(value = "栏目列表")
    public PageInfo<Section> querySectionListApi(@Min(1)
                                                   @RequestParam("page") Integer page,
                                                   @Range(min = 1, max = 100)
                                                   @RequestParam("size") Integer size) {
        return sectionService.querySectionList(page,size);
    }

    @GetMapping("/section/get/data")
    @ApiOperation(value = "栏目数据列表")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public PageInfo<SectionNewsVo> querySectionDataApi(@Min(1)
                                                 @RequestParam("page") Integer page,
                                                       @Range(min = 1, max = 100)
                                                 @RequestParam("size") Integer size) {
        return sectionService.querySectionDataList(page,size);
    }
    @PostMapping("/section/add")
    @ApiOperation(value = "添加配置")
    public Section addSectionApi(@RequestParam("name") String sectionName) {
        return sectionService.addSection(sectionName);
    }

    @GetMapping("/section/del/{id}")
    @ApiOperation(value = "删除配置")
    public void delSectionApi(@PathVariable("id") Integer id) {
        sectionService.delSection(id);
    }

    @PostMapping("/section/update")
    @ApiOperation(value = "更新配置")
    public void updateSysConfigApi(@RequestBody Section section) {
        sectionService.updateSection(section);
    }
}