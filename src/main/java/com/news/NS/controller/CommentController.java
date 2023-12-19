package com.news.NS.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.dto.Comment.*;
import com.news.NS.domain.vo.CommentAdminVo;
import com.news.NS.domain.vo.FirstAndSecondCommentVo;
import com.news.NS.domain.vo.FirstCommentVo;
import com.news.NS.domain.vo.SecondCommentVo;
import com.news.NS.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@ResponseBodyResult
@RequestMapping("/comment")
@Api(tags = "评论模块")
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    @PostMapping("/first")
    @ApiOperation(value = "返回一级评论列表")
    public PageInfo<FirstCommentVo> firstCommentsApi(@RequestBody @Valid FirstCommentListQueryDTO dto) {
        return commentService.queryFirstCommentList(dto);
    }

    @PostMapping("/first/add")
    @ApiOperation(value = "添加一级评论")
    public void addFirstCommentApi(@RequestBody @Valid FirstCommentUpdateDTO dto) {
        commentService.addFirstComment(dto);
    }

    @DeleteMapping("/first/del/{commentId}")
    @ApiOperation(value = "删除一级评论")
    public void deleteFirstCommentApi(@NotNull @ApiParam @PathVariable Integer commentId) {
        commentService.deleteFirstComment(commentId);
    }


    @PostMapping("/first/like/{commentId}")
    @ApiOperation(value = "一级评论点赞")
    public void likeFirstCommentApi(@NotNull @ApiParam @PathVariable Integer commentId) {
        commentService.likeFirstComment(commentId);
    }

    @PostMapping("/second")
    @ApiOperation(value = "返回二级评论列表")
    public PageInfo<SecondCommentVo> secondCommentsApi(@RequestBody @Valid SecondCommentListQueryDTO dto) {
        return commentService.querySecondCommentList(dto);
    }

    @PostMapping("/second/add")
    @ApiOperation(value = "添加二级评论")
    public void addSecondCommentApi(@RequestBody @Valid SecondCommentUpdateDTO dto) {
        commentService.addSecondComment(dto);
    }

    @DeleteMapping("/second/del/{commentId}")
    @ApiOperation(value = "删除二级评论")
    public void deleteSecondCommentApi(@NotNull @ApiParam @PathVariable Integer commentId) {
        commentService.deleteSecondComment(commentId);
    }


    @PostMapping("/second/like/{commentId}")
    @ApiOperation(value = "二级评论点赞")
    public void likeSecondCommentApi(@NotNull @ApiParam @PathVariable Integer commentId) {
        commentService.likeSecondComment(commentId);
    }

    @PostMapping("/")
    @ApiOperation(value = "返回一级带有二级的评论列表")
    public PageInfo<FirstAndSecondCommentVo> firstAndSecondCommentsApi(@RequestBody @Valid CommentListQueryDTO dto) {
        return commentService.queryFirstAndSecondCommentList(dto);
    }


    @PostMapping("/admin/first")
    @ApiOperation(value = "管理端返回一级评论列表")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public PageInfo<CommentAdminVo> firstCommentsListAdminApi(@RequestBody @Valid CommentListAdminQueryDTO dto) {
        return commentService.queryFirstCommentAdminList(dto);
    }
    @PostMapping("/admin/second")
    @ApiOperation(value = "管理端返回二级评论列表")
    @SaCheckRole(value = {CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN},mode = SaMode.OR)
    public PageInfo<CommentAdminVo> secondCommentsListAdminApi(@RequestBody @Valid CommentListAdminQueryDTO dto) {
        return commentService.querySecondCommentAdminList(dto);
    }
}
