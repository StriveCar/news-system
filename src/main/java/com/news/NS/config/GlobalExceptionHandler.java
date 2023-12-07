package com.news.NS.config;

import cn.dev33.satoken.exception.NotLoginException;
import com.alibaba.fastjson.JSON;
import com.news.NS.common.AlertException;
import com.news.NS.common.domain.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * 线上环境注释printStackTrace，打印错误信息
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlertException.class)
    public Result<String> alertExceptionHandler(AlertException alertException) {
        return alertException.fail();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Boolean> runtimeExceptionHandler(RuntimeException runtimeException) {
//        runtimeException.printStackTrace();
        return Result.fail(500, runtimeException.getMessage(), false);
    }

    @ExceptionHandler(ServletException.class)
    public Result<String> servletExceptionHandler(ServletException servletException) {
//        servletException.printStackTrace();
        return Result.fail(500, servletException.getMessage(), false);
    }

    // 参数校验错误处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> argumentNotValidExceptionHandler(MethodArgumentNotValidException validException) {
//        validException.printStackTrace();
        BindingResult exceptionBindingResult = validException.getBindingResult();
        Map<String, String> map = new HashMap<>();
        // 获取校验结果，遍历获取捕获到的每个校验结果
        exceptionBindingResult.getFieldErrors().forEach(item -> {
            // 存储得到的校验结果
            map.put(item.getField(), item.getDefaultMessage());
        });
        return Result.fail(5001,JSON.toJSONString(map),false);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintValidExceptionHandler(ConstraintViolationException validException) {
//        validException.printStackTrace();
        return Result.fail(5001, validException.getMessage(), false);
    }

    @ExceptionHandler(BindException.class)
    public Result<String> constraintValidExceptionHandler(BindException bindException) {
//        bindException.printStackTrace();
        BindingResult bindingResult = bindException.getBindingResult();
        Map<String, String> map = new HashMap<>();
        // 获取校验结果，遍历获取捕获到的每个校验结果
        bindingResult.getFieldErrors().forEach(item -> {
            // 存储得到的校验结果
            map.put(item.getField(), item.getDefaultMessage());
        });
        return Result.fail(5001,JSON.toJSONString(map),false);
    }

    @ExceptionHandler(NotLoginException.class)
    public Result<String> handleNotLoginException(NotLoginException e) {
//        e.printStackTrace();
        String message;
        int code;
        if (e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "请先登录";
            code = -1;
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "令牌无效";
            code = -2;
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "令牌已过期";
            code = -3;
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "您已被顶下线";
            code = -4;
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            message = "您已被踢下线";
            code = -5;
        } else {
            message = "当前会话未登录";
            code = 2001;
        }
        return Result.fail(code, message, false);
    }
}
