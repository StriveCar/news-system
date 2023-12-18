package com.news.NS.controller;

import com.news.NS.common.AlertException;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.service.NewsService;
import com.news.NS.service.UserInteractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Api(tags="用户互动")
@Validated
@ResponseBodyResult
public class UserInteractController {

    @Autowired
    UserInteractService userInteractService;

    @Autowired
    NewsService newsService;


    @PutMapping("/news/add-likes")
    @ApiOperation(value = "点赞新闻")
    public Map<String, Object> addNewsLikes(@RequestParam("newsId") Integer newsId) {

        if (newsService.addLikes(newsId) == true) {
            return null;
        } else {
            throw new AlertException(500, "新闻不存在，点赞失败");
        }
    }
}
