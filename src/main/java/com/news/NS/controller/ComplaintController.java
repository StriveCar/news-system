package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.dto.ComplaintCreateDTO;
import com.news.NS.service.ComplaintService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBodyResult
@ApiOperation(value = "举报模块")
@Validated
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService){this.complaintService = complaintService;}
    @PostMapping("/news/complaint")
    @ApiOperation(value = "举报新闻")
    public void addNewsComplaint(@RequestBody ComplaintCreateDTO complaintCreateDTO){
        complaintService.addNewComplaint(complaintCreateDTO);
    }
}
