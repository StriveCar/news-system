package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.NewsCreateDTO;
import com.news.NS.service.NewsService;
import com.news.NS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void createNews(@RequestBody NewsCreateDTO news){
        newsService.create(news);
    }

    @PostMapping("/news/publish")
    @ApiOperation(value = "发布新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void publishNews(@RequestParam("newsId") int newsId){
        newsService.publish(newsId);
    }

    @PostMapping("/news/modify")
    @ApiOperation(value = "修改新闻")
    public void modifyNews(@RequestBody News news){
        /*
         * 用于保存草稿
         * news：修改后的新闻对象
         */
        newsService.modifyNews(news);
    }

    @GetMapping("/news/delete/{ids}")
    @ApiOperation(value = "删除新闻")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void deleteNews(@PathVariable("ids") String ids){
        newsService.delete(ids);
    }

    @GetMapping("/news")
    @ApiOperation(value = "根据新闻id获取新闻详情")
    public Map<String,Object> getNewsById(@RequestParam("newsId") Integer id){
        return newsService.getNewsById(id);
    }

    @GetMapping("/news/publisherId={publisherId}")
    @ApiOperation(value = "根据作者id获取新闻")
    public PageInfo<News> getNewsByPublisherId(@PathVariable("publisherId")int publisherId,
                                             @Min(1)
                                             @RequestParam("page") Integer page,
                                             @Range(min = 1, max = 100)
                                             @RequestParam("size") Integer size){
        return newsService.getNewsByPublisherId(publisherId,page,size);
    }

    @GetMapping("/news/sectionId={sectionId}")
    @ApiOperation(value = "根据栏目id获取新闻")
    public PageInfo<News> getNewsBySectionId(@PathVariable("sectionId")int sectionId,
                                             @Min(1)
                                             @RequestParam("page") Integer page,
                                             @Range(min = 1, max = 100)
                                             @RequestParam("size") Integer size){
        return newsService.getNewsBySectionId(sectionId,page,size);
    }

    @GetMapping("/news/all")
    @ApiOperation(value = "获取全部新闻")
    public PageInfo<News> getAllNews(@Min(1)
                                     @RequestParam("page") Integer page,
                                     @Range(min = 1, max = 100)
                                     @RequestParam("size") Integer size){
        return newsService.getAllNews(page,size);
    }

    @GetMapping("/news/search")
    @ApiOperation(value = "模糊查询新闻")
    public PageInfo<News> searchNews(@RequestParam("keyword") String keyword,
                                     @Min(1)
                                     @RequestParam("page") Integer page,
                                     @Range(min = 1, max = 100)
                                     @RequestParam("size") Integer size){
        return newsService.searchNews(keyword,page,size);
    }
}
