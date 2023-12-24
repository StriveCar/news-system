package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.News.NewsCreateDTO;
import com.news.NS.domain.dto.News.NewsGetDTO;
import com.news.NS.domain.dto.News.NewsListDTO;
import com.news.NS.domain.dto.News.NewsGetByParamDTO;
import com.news.NS.domain.vo.NewsListVo;
import com.news.NS.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.Map;

@RestController
@ResponseBodyResult
@Api(tags = "新闻模块")
@Validated
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService){this.newsService = newsService;}

    @PostMapping("/news/create")
    @ApiOperation(value = "创建新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void createNews(@Valid @RequestBody NewsCreateDTO news){
        newsService.create(news);
    }

    @PostMapping("/news/publish")
    @ApiOperation(value = "发布新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void publishNews(@NotNull @RequestParam("newsId") Integer newsId){
        newsService.publish(newsId);
    }

    @PostMapping("/news/modify")
    @ApiOperation(value = "修改新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void modifyNews(@RequestBody News news){
        newsService.modifyNews(news);
    }

    @PostMapping("/news/delete/{ids}")
    @ApiOperation(value = "删除新闻")
    @SaCheckRole(value = {CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void deleteNews(@NotNull @PathVariable("ids") String ids){
        newsService.delete(ids);
    }

    @PostMapping("/news/get/by-id")
    @ApiOperation(value = "根据id获取新闻详情")
    public Map<String,Object> getNewsById(@Valid NewsGetDTO newsGetDTO){
        return newsService.getNewsById(newsGetDTO);
    }

    @PostMapping("/news/get/by-publisher")
    @ApiOperation(value = "根据作者id获取新闻")
    public PageInfo<News> getNewsByPublisherId(@Valid @RequestBody NewsGetByParamDTO<Integer> dto){
        return newsService.getNewsByPublisherId(dto);
    }

    @PostMapping("/news/get/by-section")
    @ApiOperation(value = "根据栏目id获取新闻")
    public PageInfo<News> getNewsBySectionId(@Valid @RequestBody NewsGetByParamDTO<Integer> dto){
        return newsService.getNewsBySectionId(dto);
    }

    @PostMapping("/news/get/by-status")
    @ApiOperation(value = "根据新闻状态获取新闻")
    public PageInfo<News> getNewsByPublishStatus(@Valid @RequestBody NewsGetByParamDTO<Byte> dto){
        return newsService.getNewsByPublishStatus(dto);
    }

    @PostMapping("/news/list")
    @ApiOperation(value = "获取新闻列表")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public PageInfo<NewsListVo> getNewsList(@RequestBody NewsListDTO newsListDTO){
        return newsService.getNewsList(newsListDTO);
    }

    @PostMapping("/news/search")
    @ApiOperation(value = "模糊查询新闻")
    public PageInfo<News> searchNews(@Valid @RequestBody NewsGetByParamDTO<String> dto){
        return newsService.searchNews(dto);
    }


}
