package com.news.NS.controller;


import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.dto.*;
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
    public void likeFirstCommentApi(@RequestBody @Valid LikeDTO dto) {
        commentService.likeFirstComment(dto.getCommentId(), dto.getUserId());
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
    public void deleteSecondCommentApi(
            @NotNull @ApiParam(value = "删除的评论Id") @PathVariable Integer commentId
    ) {
        commentService.deleteSecondComment(commentId);
    }


    @PostMapping("/second/like/{commentId}")
    @ApiOperation(value = "二级评论点赞")
    public void likeSecondCommentApi(@RequestBody @Valid LikeDTO dto) {
        commentService.likeSecondComment(dto.getCommentId(), dto.getUserId());
    }

    @PostMapping("/")
    @ApiOperation(value = "返回一级带有二级的评论列表")
    public PageInfo<FirstAndSecondCommentVo> firstAndSecondCommentsApi(@RequestBody @Valid CommentListQueryDTO dto) {
        return commentService.queryFirstAndSecondCommentList(dto);
    }


    @PostMapping("/admin/first")
    @ApiOperation(value = "管理端返回一级评论列表")
    public PageInfo<CommentAdminVo> firstCommentsListAdminApi(@RequestBody @Valid CommentListAdminQueryDTO dto) {
        return commentService.queryFirstCommentAdminList(dto);
    }
    @PostMapping("/admin/second")
    @ApiOperation(value = "管理端返回二级评论列表")
    public PageInfo<CommentAdminVo> secondCommentsListAdminApi(@RequestBody @Valid CommentListAdminQueryDTO dto) {
        return commentService.querySecondCommentAdminList(dto);
    }
}
