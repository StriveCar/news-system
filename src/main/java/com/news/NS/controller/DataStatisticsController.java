package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.vo.PulisherDataVo;
import com.news.NS.service.DataStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 车
 * @date 2023/12/9 12 13
 * discription
 */
@RestController
@ResponseBodyResult
@Api(tags = "数据统计")
@Validated
public class DataStatisticsController {

    private final DataStatisticsService dataStatisticsService;

    public DataStatisticsController(DataStatisticsService dataStatisticsService) {
        this.dataStatisticsService = dataStatisticsService;
    }

    @GetMapping("/admin/get/sys/count")
    @ApiOperation(value = "获取系统统计信息")
    @SaCheckRole(value = {CommonConstant.ADMIN, CommonConstant.SUPER_ADMIN}, mode = SaMode.OR)
    public Map<String, Object> getSystemCount() {
        return dataStatisticsService.getSystemCount();
    }

    @GetMapping("/admin/get/pulisher/top")
    @ApiOperation(value = "获取热门编辑榜单信息")
    @SaCheckRole(value = {CommonConstant.ADMIN, CommonConstant.SUPER_ADMIN}, mode = SaMode.OR)
    public Map<String, Object> getPulisherTop() {
        return dataStatisticsService.getPulisherTop();
    }

    @GetMapping("/admin/get/role/count")
    @ApiOperation(value = "获取角色分类信息")
    @SaCheckRole(value = {CommonConstant.ADMIN, CommonConstant.SUPER_ADMIN}, mode = SaMode.OR)
    public Map<String, Object> getRoleData() {
        return dataStatisticsService.getRolesCount();
    }

    @GetMapping("/admin/get/section/count")
    @ApiOperation(value = "获取栏目统计信息")
    @SaCheckRole(value = {CommonConstant.ADMIN, CommonConstant.SUPER_ADMIN}, mode = SaMode.OR)
    public Map<String, Object> getSectionData() {
        return dataStatisticsService.getSectionData();
    }
}
