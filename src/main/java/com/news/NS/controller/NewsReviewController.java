package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.service.NewsReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 车
 * @date 2023/12/6 18 26
 * discription
 */
@Controller
@ResponseBodyResult
@Api(tags = "新闻审核")
@Validated
public class NewsReviewController {
    @Autowired
    private NewsReviewService newsReviewService;

//    @GetMapping("/newsReview/cancel")
//    @ApiOperation(value = "取消审批")
//    public void cancelApply(@NotEmpty @RequestParam(value = "newsId") String newsId) {
//        newsReviewService.cancelApply(roomReservationId);
//    }
//
//    @PostMapping("/roomReservation/queryMyApply")
//    @ApiOperation(value = "查询我的预约")
//    public PageInfo<RoomReservationVo> queryMyApply(@Valid @RequestBody MyApplyQueryDTO myApplyQueryDTO) {
//        return newsReviewService.queryMyApply(myApplyQueryDTO);
//    }
//
//    @PostMapping("/roomReservation/queryRoomApplyDetailList")
//    @ApiOperation("查询房间预约详细信息")
//    @SaCheckRole(value = {CommonConstant.ADMIN_ROLE, CommonConstant.SUPER_ADMIN_ROLE}, mode = SaMode.OR)
//    public PageInfo<RoomReservationUserVo> queryRoomApplyDetailList(@Valid @RequestBody RoomApplyDetailListQueryDTO roomApplyDetailListQueryDTO) {
//        return newsReviewService.queryRoomApplyDetailList(roomApplyDetailListQueryDTO);
//    }
//
//    @PostMapping("/roomReservation/userRecord")
//    @ApiOperation("查询用户预约详细信息")
//    @SaCheckRole(value = {CommonConstant.ADMIN_ROLE, CommonConstant.SUPER_ADMIN_ROLE}, mode = SaMode.OR)
//    public PageInfo<RoomReservationVo> queryUserReserveRecordApi(@Valid @RequestBody UserRoomReservationDetailQueryDTO userRoomReservationDetailQueryDTO) {
//        return newsReviewService.queryUserReserveRecord(userRoomReservationDetailQueryDTO);
//    }
//
//    @PostMapping("/roomReservation/reviewed/userRecord")
//    @ApiOperation("查询待审核房间列表信息")
//    @SaCheckRole(value = {CommonConstant.ADMIN_ROLE, CommonConstant.SUPER_ADMIN_ROLE}, mode = SaMode.OR)
//    public PageInfo<RoomReservationAdminVo> queryRoomReserveToBeReviewedApi(@Valid @RequestBody RoomReserveReviewedDTO roomReserveReviewedDTO) {
//        return newsReviewService.queryRoomReserveToBeReviewed(roomReserveReviewedDTO);
//    }
//
//    @GetMapping("/roomReservation/approval")
//    @ApiOperation("审批房间预约")
//    @SaCheckRole(value = {CommonConstant.ADMIN_ROLE, CommonConstant.SUPER_ADMIN_ROLE}, mode = SaMode.OR)
//    public void passOrRejectReserveApi(@NotEmpty @RequestParam("reserveId") String reserveId,
//                                       @NotNull @RequestParam("passed") boolean passed,
//                                       @RequestParam(value = "rejectReason", required = false) String rejectReason) {
//        newsReviewService.passOrRejectReserve(reserveId, passed, rejectReason);
//    }
//
//    @GetMapping("/roomReservation/del/record")
//    @ApiOperation("删除房间预约记录")
//    @SaCheckRole(value = {CommonConstant.ADMIN_ROLE, CommonConstant.SUPER_ADMIN_ROLE}, mode = SaMode.OR)
//    public void delRoomReservationRecordApi(@NotEmpty @RequestParam("id") String id) {
//        newsReviewService.delRoomReservationRecord(id);
//    }
}
