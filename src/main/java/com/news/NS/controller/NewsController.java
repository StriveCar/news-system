package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.NewsCreateDTO;
import com.news.NS.service.NewsService;
import com.news.NS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntToDoubleFunction;

@RestController
@ResponseBodyResult
@Api(tags = "新闻模块")
@Validated
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService){this.newsService = newsService;}

    @PostMapping("/news/create")
    @ApiOperation(value = "创建新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public boolean createNews(@RequestBody NewsCreateDTO news){
        return newsService.create(news);
    }


    @PostMapping("/news/publish/{id}")
    @ApiOperation(value = "发布新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public boolean publishNews(@PathVariable("id") int newsId){
        return newsService.publish(newsId);
    }

    @PostMapping("/news/delete/{ids}")
    @ApiOperation(value = "删除新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public boolean deleteNews(@PathVariable("ids") String ids){
        return newsService.delete(ids);
    }

    @PostMapping("/news")
    @ApiOperation(value = "根据新闻id获取新闻")
    public Map<String,Object> getNewsById(@RequestParam Integer id){
        return newsService.getNewsById(id);
    }

    @GetMapping("/news/sectionId={sectionId}")
    @ApiOperation(value = "获取栏目新闻")
    public Map<String,Object> getNewsBySectionId(@PathVariable("sectionId")int sectionId){
        return newsService.getNewsBySectionId(sectionId);
    }
}
