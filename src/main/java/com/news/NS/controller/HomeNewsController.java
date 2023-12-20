package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.vo.HomeHotNewsVo;
import com.news.NS.domain.vo.HomePictureNewsVo;
import com.news.NS.service.HomeNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/home")
@ResponseBodyResult
@Api(tags = "首页新闻")
public class HomeNewsController {
    @Autowired
    HomeNewsService homeNewsService;

    /**
     * 获取热门新闻列表
     * 热门计算方式：7个权重的点赞+3个权重的浏览量
     * @return 热门新闻列表
     */
    @GetMapping("/news/hot")
    @ApiOperation(value = "获取热门新闻列表")
    public List<HomeHotNewsVo> getHotNews() {
        return homeNewsService.getHotNews();
    }

    /**
     * 获取首页大图新闻列表
     *首页的大图新闻 选择方案：首先得是图片新闻，其实在图片新闻中选择最新发布的 5 条新闻
     * @return 首页图片新闻列表
     */
    @GetMapping("/news/picturenews")
    @ApiOperation(value = "获取首页图片新闻列表")
    public List<HomePictureNewsVo> getHomePictureNews() {
        return homeNewsService.getHomePictureNews();
    }


}
