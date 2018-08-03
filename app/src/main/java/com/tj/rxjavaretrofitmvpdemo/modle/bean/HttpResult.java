package com.tj.rxjavaretrofitmvpdemo.modle.bean;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:32
 * @描述:
 */
public class HttpResult<T> {

    private int    code;
    private String msg;
    private T      data;

    public HttpResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
