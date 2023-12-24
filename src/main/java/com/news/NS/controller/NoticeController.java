package com.news.NS.controller;

import com.news.NS.common.ResponseBodyResult;
import com.news.NS.domain.Notification;
import com.news.NS.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@ResponseBodyResult
@Api(tags = "通知")
@RequestMapping("/notice")
@Validated
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @PostMapping("/set")
    @ApiOperation(value = "发送通知")
    public Map<String,Object> setNotice(@RequestBody Notification notification) {
      return   noticeService.addNotice(notification);
    }

    @PutMapping("/read")
    @ApiOperation(value = "更新通知读状态")
    public Map<String, Object> readNotice(@RequestParam("notificationId") Integer notificationId) {
        return noticeService.updateReadState(notificationId);

    }

    @GetMapping("/select/all")
    @ApiOperation(value = "查询用户全部通知")
    public List<Notification> selectAllNotice(@RequestParam("userId") Integer userId) {
        return noticeService.selectAllNotice(userId);
    }

    /**
     * 查询用户已读/未读
     *
     * @param userId 用户ID
     * @param flag   1已读，0未读
     * @return 查询结果
     */
    @GetMapping("/select/isread")
    @ApiOperation(value = "查询已读/未读通知")
    public List<Notification> selectUnreadNotice(@RequestParam("userId") Integer userId,
                                                 @RequestParam("flag") @Range(min = 1,max=2,message = "非法参数") Byte flag) {

        return noticeService.selectIsReadNotice(userId,flag);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除通知")
    public Map<String, Object> deleteNotice(@RequestParam("notificationId") Integer notificationId) {
        return noticeService.deleteNotice(notificationId);
    }

}
