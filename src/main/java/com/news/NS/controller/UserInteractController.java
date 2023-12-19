package com.news.NS.controller;

import com.news.NS.common.AlertException;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.Result;
import com.news.NS.domain.Collect;

import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import com.news.NS.domain.vo.CollectNewsOv;
import com.news.NS.domain.vo.FocusVo;
import com.news.NS.service.NewsService;
import com.news.NS.service.UserInteractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
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

    /**
     * 点赞新闻
     * @param newsId
     * @return
     */
    @PutMapping("/news/add-likes")
    @ApiOperation(value = "点赞新闻")
    public Map<String, Object> addNewsLikes(@RequestParam("newsId") Integer newsId,@RequestParam("userId") Integer userId) {

        if (userInteractService.addLikes(newsId,userId) == true) {
            return null;
        } else {
            throw new AlertException(500, "新闻不存在，点赞失败");
        }
    }

    /**
     * 收藏新闻
     * @param collect
     * @return
     */
    @PostMapping("/user/collect-news")
    @ApiOperation(value="收藏新闻")
    public Result collectNews(@RequestBody @Valid Collect collect){

       if (userInteractService.addCollectInfo(collect)>0)
            return Result.success("收藏成功");
       else
           return Result.fail("收藏失败");
    }

    @DeleteMapping("/user/uncollect-news")
    @ApiOperation(value="取消收藏")
    public Result uncollectNews(@RequestBody @Valid Collect collect){
        if(userInteractService.deletCollectInfo(collect)>0){
            return Result.success("取消收藏成功");
        }else {
            return Result.fail("取消收藏失败");
        }
    }

    @GetMapping("/user/collect-list")
    @ApiOperation(value="获取收藏列表")
    public List<CollectNewsOv> getCollectNewsList(@RequestParam("userId") Integer userId){
        return userInteractService.getCollectNewsList(userId);
    }

    @PostMapping("/user/focus")
    @ApiOperation(value="用户关注")
    public Result focus(@RequestBody UserFocusDTO userFocusDTO){

        if(userInteractService.focusUser(userFocusDTO) >0)
            return Result.success("关注成功");
        else
            return Result.fail("关注失败");
    }
    @DeleteMapping("/user/unfocus")
    @ApiOperation(value = "取消关注")
    public Result unfocus(@RequestBody UserFocusDTO userFocusDTO){
        if(userInteractService.unfocusUser(userFocusDTO) >0 )
            return Result.success("取消关注成功");
        else
            return Result.fail("取消关注失败");
    }

    @GetMapping("/user/focus")
    @ApiOperation(value="获取用户关注列表")
    public List<FocusVo> getFocusList(@RequestParam("userId") Integer userId){
        return userInteractService.getFocusList(userId);
    }

    @GetMapping("/user/follows")
    @ApiOperation(value="获取用户粉丝列表")
    public List<FocusVo> getFollowsList(@RequestParam("focusedUserId") Integer focusedUserId){
       return userInteractService.getFollowsList(focusedUserId);
    }
}
