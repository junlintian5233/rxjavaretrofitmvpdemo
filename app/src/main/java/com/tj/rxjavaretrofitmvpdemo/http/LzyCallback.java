package com.tj.rxjavaretrofitmvpdemo.http;


import com.tj.rxjavaretrofitmvpdemo.modle.bean.APIException;
import com.tj.rxjavaretrofitmvpdemo.utils.LogUtils;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:55
 * @描述: 啥也没有处理的回调
 */
public abstract class LzyCallback<T> implements HttpCallback<T> {

    @Override
    public void onComplete() {
    }

    /**
     * 处理服务器 返回rt 不为1 情况下的异常
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof APIException) {
            APIException apiException = (APIException) e;
            LogUtils.e("LzyCallback", "onError->----APIException:" + apiException.getMsg() + "  当前线程：" + Thread.currentThread().getName());
        } else {
            LogUtils.e("LzyCallback", "onError->----其他异常: " + e.toString());
        }
    }

    @Override
    public abstract void onNext(T t);

}
