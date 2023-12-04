package com.news.NS.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(ResultCode resultCode, T data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data == null ? (T) Boolean.valueOf(resultCode.isSuccess()) : data;
    }

    public Result(Integer code, String message, boolean success){
        this.code = code;
        this.message = message;
        this.data = (T) Boolean.valueOf(success);
    }

    //成功

    public static <T> Result<T> success(T data){
        return new Result<>(ResultCode.SUCCESS,data);
    }

    //失败
    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        result.setData((T) Boolean.valueOf(false));
        return result;
    }

    public static <T> Result<T> fail(int code, String message, boolean success) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData((T) Boolean.valueOf(success));
        return result;
    }

}
