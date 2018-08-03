package com.tj.rxjavaretrofitmvpdemo.modle.bean;

import java.io.IOException;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:56
 * @描述: 服务器定义的返回异常
 */
public class APIException extends IOException {
    private int    code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public APIException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
