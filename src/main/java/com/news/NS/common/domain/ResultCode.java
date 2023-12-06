package com.news.NS.common.domain;

public enum ResultCode {
    //成功状态码
    SUCCESS(200,"操作成功", true),
    FAILED(500, "未知错误", false),

    //参数错误 400-499
    PARAM_IS_INVALID(400,"参数无效", false),
    PARAM_IS_BLANK(402,"参数为空", false),
    PARAM_IS_TIME_OUT(403, "参数失效", false),
    PARAM_TYPE_BIND_ERROR(404,"参数类型错误", false),
    PARAM_NOT_COMPLETE(405,"参数缺失", false),
    PARAM_IS_ILLEGAL(406,"非法参数", false),
    ILLEGAL_OPERATION(407,"非法操作", false),

    SYSTEM_ERROR(500,"服务器异常", false),
    UPDATE_ERROR(500,"更新数据失败", false),
    INSERT_ERROR(500,"添加数据失败", false),
    DELETE_ERROR(500,"删除数据失败", false),

    //用户错误 2001~2999
    USER_NOT_LOG_IN(2001,"用户未登录", false),
    ACCOUNT_ERROR(2002,"用户不存在，账号密码错误，账号状态异常，或账号被禁用", false),
    USER_ACCOUNT_EXISTED(2003,"账户已经存在", false),
    USER_NOT_EXIST(2004,"用户不存在", false),
    TOKE_INFO_ERROR(2005,"用户授权信息错误", false),
    USER_NICKNAME_ALREADY_EXISTS(2006, "操作昵称已经存在", false),
    ACCOUNT_TO_BE_IMPROVED(2007,"用户信息未完善", false),
    TEL_ERROR(2008,"查询不到该手机号",false),
    TEL_EXIST(2009,"该手机号已存在",false),

    SECTION_EXIST(2009,"该栏目已存在",false),
    SECTION_NOT_EXIST(2009,"该栏目不存在",false),

    OPERATE_OBJECT_NOT_SELF(2012, "操作的对象不能是自己", false),

    UPDATE_USERINFO_OUT_OF_TIMES(2013, "修改个人信息次数达到最大，请24小时后修改", false),


    PERMISSION_REJECTED(4001, "权限不足", false),
    REPEAT_OPERATION(4003,"重复操作", false),
    UPDATE_USER_ROLE_IS_NOT_PERMITTED(4004,"不允许删除普通用户权限", false),

    ;


    private Integer code;
    private String message;
    private boolean success;

    ResultCode(Integer code, String message, boolean success) {
        this.message = message;
        this.code = code;
        this.success = success;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
