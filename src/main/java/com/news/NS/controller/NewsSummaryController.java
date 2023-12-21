package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.vo.HotNewsVo;
import com.news.NS.domain.vo.BigPictureNewsVo;
import com.news.NS.domain.vo.NewsSummaryVo;
import com.news.NS.service.NewsSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/home")
@ResponseBodyResult
@Api(tags = "首页新闻")
public class NewsSummaryController {
    @Autowired
    NewsSummaryService newsSummaryService;

    /**
     * 获取热门新闻列表的概述信息，只包含新闻title 和 新闻id
     * 热门计算方式：7个权重的点赞+3个权重的浏览量
     * @param sectionId 新闻分类id,非必须参数,如果不传则在所有所有新闻中选择最热门的，用于在首页展示
     * @param num 返回新闻数量，非必须参数，默认10个
     * @return 热门/要闻新闻列表
     */
    @GetMapping("/news/hot")
    @ApiOperation(value = "获取热门新闻列表")
    public List<HotNewsVo> getHotNews(Integer sectionId, @RequestParam(value = "num",defaultValue = "10") Integer num) {
        return newsSummaryService.getHotNews(sectionId,num);
    }


    /**
     * 获取大图新闻列表
     * 获取大图新闻列表的方案选择：首先必须是图片新闻，其次在图片新闻中选择最新发布的 5 条新闻
     * @param sectionId 新闻分类ID，非必须参数，如果不传入就代表主页中要展示的顶部大图片，传入就是对应栏目顶部的大图片
     * @param num 返回新闻数量，非必须参数，默认5条
     * @return 首页图片新闻列表
     */
    @GetMapping("/news/picturenews")
    @ApiOperation(value = "获取大图片新闻列表")
    public List<BigPictureNewsVo> getHomePictureNews(Integer sectionId, @RequestParam(value = "num", defaultValue = "5") Integer num) {
        return newsSummaryService.getHomePictureNews(sectionId, num);
    }

    /**
     * 获取新闻基本信息
     * 通过sectionId和num参数获取指定新闻分类的新闻基本信息
     *新闻id,标题，发布时间，新闻封面图片，发布人id，发布人用户名，发布人头像
     * @param sectionId 新闻分类ID,非必须参数，不传入就默认在所有新闻中获取
     * @param num 返回的新闻数量,非必须参数一次要查询的新闻数量，默认5条
     * @return 指定新闻分类的新闻基本信息列表
     */
    @GetMapping("/news/summary")
    @ApiOperation(value = "获取新闻基本信息")
    public List<NewsSummaryVo> getNewsSummary(Integer sectionId, @RequestParam(value = "num", defaultValue = "5") Integer num) {
        return newsSummaryService.getNewsSummary(sectionId, num);
    }


}
