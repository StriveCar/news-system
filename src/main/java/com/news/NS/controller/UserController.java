package com.news.NS.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.*;
import com.news.NS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@ResponseBodyResult
@Api(tags = "用户模块")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录")
    public Map<String, Object> loginApi(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/user/update/userInfo")
    @ApiOperation(value = "修改用户信息")
    public void updateUserInfoApi(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUserInfo(userUpdateDTO);
    }

    @GetMapping("/user/get/verifyCode")
    @ApiOperation(value = "获取六位验证码")
    public void getVerifyCodeApi(@RequestParam("tel") String tel) {
        userService.getVerifyCode(tel);
    }

    @PostMapping("/user/modify/password")
    @ApiOperation(value = "修改用户密码")
    public void updateUserPasswordApi(@Valid @RequestBody UserUpdatePwdDTO userUpdateDTO) {
        userService.updateUserPassword(userUpdateDTO);
    }

    @PostMapping("/admin/query/userList")
    @ApiOperation(value = "查询用户列表")
    public PageInfo<User> queryUserListApi(@Valid @RequestBody UserListQueryDTO userListQueryDTO) {
        return userService.queryUserList(userListQueryDTO);
    }

    @PostMapping("/admin/update/role")
    @ApiOperation(value = "更改用户权限")
    public void changeRoleApi(@RequestBody UserRoleChangeDTO userRoleChangeDTO) {
        userService.changeUserRole(userRoleChangeDTO.getUserId(), userRoleChangeDTO.getIdentification());
    }


}

