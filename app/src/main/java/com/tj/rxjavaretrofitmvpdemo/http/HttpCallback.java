package com.tj.rxjavaretrofitmvpdemo.http;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:55
 * @描述: 网络请求返回
 */
public interface HttpCallback<T> {

    void onComplete();

    void onError(Throwable e);

    void onNext(T t);
}