package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.News;
import com.news.NS.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.function.IntToDoubleFunction;

@RestController
@ResponseBodyResult
@RequestMapping("/news")
@Api(tags = "新闻模块")
@Validated
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService){this.newsService = newsService;}

    @PostMapping
    @ApiOperation(value = "创建新闻")
    @RequestMapping("/create")
    public boolean create(@Valid @RequestBody News news){
        return newsService.create(news);
    }

    @PostMapping
    @ApiOperation(value = "发布新闻")
    @RequestMapping()
    public boolean publish(@Valid @RequestBody int newsId){
        return newsService.publish(newsId);
    }

    @PostMapping
    @ApiOperation(value = "删除")
    @RequestMapping("/{ids}")
    public int delete(@PathVariable("ids") String ids){
        return newsService.delete(ids);
    }

    @GetMapping("/sectionId={sectionId}")
    @ApiOperation(value = "获取栏目新闻")
    public Map<String,Object> getNewsBySectionId(@PathVariable("sectionId")int sectionId){
        return newsService.getNewsBySectionId(sectionId);
    }
}
