package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.Complaint;
import com.news.NS.domain.dto.ComplaintCreateDTO;
import com.news.NS.domain.dto.ComplaintDeleteDTO;
import com.news.NS.domain.dto.ComplaintModifyDTO;
import com.news.NS.domain.dto.ComplaintSearchDTO;
import com.news.NS.service.ComplaintService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBodyResult
@ApiOperation(value = "举报模块")
@Validated
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService){this.complaintService = complaintService;}
    @PostMapping("/complaint/create")
    @ApiOperation(value = "添加举报")
    @SaCheckRole(value = {CommonConstant.USER,CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void addComplaint(@RequestBody ComplaintCreateDTO complaintCreateDTO){
        complaintService.addNewComplaint(complaintCreateDTO);
    }
    @GetMapping("/complaint/delete")
    @ApiOperation(value = "删除举报")
    @SaCheckRole(value = {CommonConstant.USER,CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void deleteComplaint(@RequestBody ComplaintDeleteDTO dto){
        complaintService.deleteComplaint(dto);
    }

    @PostMapping("/complaint/modify")
    @ApiOperation(value = "修改举报信息")
    @SaCheckRole(value = {CommonConstant.USER,CommonConstant.ADMIN,CommonConstant.SUPER_ADMIN,CommonConstant.PULISHER},mode = SaMode.OR)
    public void modifyComplaint(@RequestParam ComplaintModifyDTO dto){
        complaintService.modifyComplaint(dto);
    }

    @GetMapping("/complaint/search")
    @ApiOperation(value = "查询举报")
    public PageInfo<Complaint> searchComplaint(@RequestBody ComplaintSearchDTO<String> dto){
        return complaintService.searchComplaint(dto);
    }
}
