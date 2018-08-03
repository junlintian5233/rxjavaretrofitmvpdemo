package com.tj.rxjavaretrofitmvpdemo.utils;

import android.util.Log;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:59
 * @描述: 日志管理
 */
public class LogUtils {

    public static boolean isDebug = true;

    public static void setDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }


    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }
}
