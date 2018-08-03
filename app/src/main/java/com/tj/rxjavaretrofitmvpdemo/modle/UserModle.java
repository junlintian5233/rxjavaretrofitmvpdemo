package com.tj.rxjavaretrofitmvpdemo.modle;

import com.tj.rxjavaretrofitmvpdemo.base.BaseModel;
import com.tj.rxjavaretrofitmvpdemo.http.LzyCallback;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.UserInfo;

/**
 * @作者: TJ
 * @时间: 2018/8/2 16:49
 * @描述: 从Presenter传过来数据，然后进行网络请求以及对请求的数据处理等，此类可以复用，只要有相同的请求都可以复用
 */
public class UserModle extends BaseModel {

    public void getUserData(long id, LzyCallback<UserInfo> callback) {
//        Observable<HttpResult<List<UserInfo>>> observable = mApiService.getUserInfo(id);
//        applySchedulers(observable);
//        observable.subscribe(this.<HttpResult<List<UserInfo>>>newObserver(callback));
        //简化一下
        applySchedulers(mApiService.getUserInfo(id)).subscribe(newObserver(callback));
    }
}
