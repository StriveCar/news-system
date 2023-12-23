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
import com.news.NS.domain.dto.News.NewsSearchParamDTO;
import com.news.NS.domain.vo.NewsListVo;
import com.news.NS.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    public void createNews(@RequestBody NewsCreateDTO news){
        newsService.create(news);
    }

    @PostMapping("/news/publish")
    @ApiOperation(value = "发布新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void publishNews(@RequestParam("newsId") Integer newsId){
        newsService.publish(newsId);
    }

    @PostMapping("/news/modify")
    @ApiOperation(value = "修改新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void modifyNews(@RequestBody News news){
        newsService.modifyNews(news);
    }

    @GetMapping("/news/delete/{ids}")
    @ApiOperation(value = "删除新闻")
    @SaCheckRole(value = {CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void deleteNews(@PathVariable("ids") String ids){
        newsService.delete(ids);
    }

    @GetMapping("/news/get/by-id")
    @ApiOperation(value = "根据新闻id获取新闻详情")
    public Map<String,Object> getNewsById(NewsGetDTO newsGetDTO){
        return newsService.getNewsById(newsGetDTO);
    }

    @PostMapping("/news/get/by-publisher")
    @ApiOperation(value = "根据作者id获取新闻")
    public PageInfo<News> getNewsByPublisherId(@Min(1)
                                               @RequestParam("page") Integer page,
                                               @Range(min = 1, max = 100)
                                               @RequestParam("size") Integer size,
                                               @RequestParam Integer id){
        return newsService.getNewsByPublisherId(page,size,id);
    }

    @PostMapping("/news/get/by-section")
    @ApiOperation(value = "根据栏目id获取新闻")
    public PageInfo<News> getNewsBySectionId(@Min(1)
                                             @RequestParam("page") Integer page,
                                             @Range(min = 1, max = 100)
                                             @RequestParam("size") Integer size,
                                             @RequestParam Integer id){
        return newsService.getNewsBySectionId(page,size,id);
    }

    @GetMapping("/news/get/by-status")
    @ApiOperation(value = "根据新闻状态获取新闻")
    public PageInfo<News> getNewsByPublishStatus(@Min(1)
                                                 @RequestParam("page") Integer page,
                                                 @Range(min = 1, max = 100)
                                                 @RequestParam("size") Integer size,
                                                 @Range(min = 1, max = 4)
                                                 @RequestParam Byte status){
        return newsService.getNewsByPublishStatus(page,size,status);
    }

    @PostMapping("/news/list")
    @ApiOperation(value = "获取新闻列表")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public PageInfo<NewsListVo> getNewsList(@RequestBody NewsListDTO newsListDTO){
        return newsService.getNewsList(newsListDTO);
    }

    @GetMapping("/news/search")
    @ApiOperation(value = "模糊查询新闻")
    public PageInfo<News> searchNews(@Min(1)
                                     @RequestParam("page") Integer page,
                                     @Range(min = 1, max = 100)
                                     @RequestParam("size") Integer size,
                                     @RequestParam String key){
        return newsService.searchNews(page,size,key);
    }
}
