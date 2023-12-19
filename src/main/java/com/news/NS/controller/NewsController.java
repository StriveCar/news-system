package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.NewsCreateDTO;
import com.news.NS.domain.dto.NewsListDTO;
import com.news.NS.domain.dto.NewsSearchParamDTO;
import com.news.NS.domain.vo.NewsListVo;
import com.news.NS.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
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
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void deleteNews(@PathVariable("ids") String ids){
        newsService.delete(ids);
    }

    @GetMapping("/news/get/by-id")
    @ApiOperation(value = "根据新闻id获取新闻详情")
    public Map<String,Object> getNewsById(@RequestParam("newsid") Integer id){
        return newsService.getNewsById(id);
    }

    @GetMapping("/news/get/by-publisher")
    @ApiOperation(value = "根据作者id获取新闻")
    public PageInfo<News> getNewsByPublisherId(@RequestBody NewsSearchParamDTO<Integer> dto){
        return newsService.getNewsByPublisherId(dto);
    }

    @GetMapping("/news/get/by-section")
    @ApiOperation(value = "根据栏目id获取新闻")
    public PageInfo<News> getNewsBySectionId(@RequestBody NewsSearchParamDTO<Integer> dto){
        return newsService.getNewsBySectionId(dto);
    }

    @GetMapping("/news/get/by-status")
    @ApiOperation(value = "根据新闻状态获取新闻")
    public PageInfo<News> getNewsByPublishStatus(@RequestBody NewsSearchParamDTO<Byte> dto){
        return newsService.getNewsByPublishStatus(dto);
    }

    @PostMapping("/news/list")
    @ApiOperation(value = "获取新闻列表")
    public PageInfo<NewsListVo> getNewsList(@RequestBody NewsListDTO newsListDTO){
        return newsService.getNewsList(newsListDTO);
    }

    @GetMapping("/news/search")
    @ApiOperation(value = "模糊查询新闻")
    public PageInfo<News> searchNews(@RequestBody NewsSearchParamDTO<String> dto){
        return newsService.searchNews(dto);
    }
}
