package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.dto.ApplicationCreateDTO;
import com.news.NS.domain.dto.ApplicationListQueryDTO;
import com.news.NS.domain.vo.ApplicationListVo;
import com.news.NS.service.ApplicationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author 车
 * &#064;date  2023/12/23 18 24
 * discription
 */
@RestController
@ResponseBodyResult
@Api(tags = "新闻编辑申请模块")
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/add")
    public void addApplication(@Valid @RequestBody ApplicationCreateDTO applicationCreateDTO) {
        applicationService.addApplication(applicationCreateDTO);
    }

    @DeleteMapping("/del/{userId}")
    public void deleteApplication(@NotNull @PathVariable Integer userId) {
        applicationService.delApplication(userId);
    }

    @PutMapping
    public void updateApplication(@Valid @RequestBody ApplicationCreateDTO applicationCreateDTO) {
        applicationService.updateApplication(applicationCreateDTO);
    }

    @PostMapping("/list")
    public PageInfo<ApplicationListVo> queryApplicationList(@Valid @RequestBody ApplicationListQueryDTO applicationListQueryDTO) {
        return applicationService.queryApplicationList(applicationListQueryDTO);
    }

    @GetMapping("/{userId}")
    public Map<String, Object> queryApplication(@NotNull @PathVariable Integer userId) {
        return applicationService.queryMyApplication(userId);
    }

    @PostMapping("/pass")
    public void passApplication(@NotNull @RequestParam("userId") Integer userId,
                                @RequestParam("pass") boolean pass,
                                @RequestParam("remark") String remark) {
        applicationService.passApplication(userId, pass, remark);
    }
}
