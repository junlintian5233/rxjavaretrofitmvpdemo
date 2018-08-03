package com.tj.rxjavaretrofitmvpdemo.http;


import com.tj.rxjavaretrofitmvpdemo.modle.bean.HttpResult;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @作者: TJ
 * @时间: 2018/7/23 16:26
 * @描述: retrofit请求的api
 */
public interface RetrofitApiService {

    /**
     * 获取banner
     */
    @GET("v1/userinfo")
    Observable<HttpResult<UserInfo>> getUserInfo(@Query("id") long id);
}
