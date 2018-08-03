package com.tj.rxjavaretrofitmvpdemo;

import android.app.Application;

import com.tj.rxjavaretrofitmvpdemo.utils.LogUtils;

/**
 * @作者: TJ
 * @时间: 2018/8/2 16:43
 * @描述:
 */
public class DemoApp extends Application {

    private static DemoApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        LogUtils.setDebug(true);
    }

    public static DemoApp getApplication() {
        return application;
    }
}
