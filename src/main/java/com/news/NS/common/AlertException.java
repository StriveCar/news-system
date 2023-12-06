package com.news.NS.common;


import com.news.NS.common.domain.Result;
import com.news.NS.common.domain.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertException extends RuntimeException {

    private int code;

    private String message;

    private boolean success;

    public AlertException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.success = resultCode.isSuccess();
    }

    public AlertException(int code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }

}

