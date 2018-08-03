package com.tj.rxjavaretrofitmvpdemo.base;

/**
 * @作者: TJ
 * @时间: 2018/4/11 12:01
 * @描述: 连接P层与V层的接口，在这个基类可以定义一些常用的回调，例如：一个Toast显示
 */
public interface BaseView {

    /**
     * 显示提示消息
     */
    void showToastMessage(String message);

    /**
     * 显示提示消息
     */
    void showToastMessage(int message);
}
