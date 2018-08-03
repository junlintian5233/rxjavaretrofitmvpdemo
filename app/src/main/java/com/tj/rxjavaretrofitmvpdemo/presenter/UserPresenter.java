package com.tj.rxjavaretrofitmvpdemo.presenter;

import android.content.Context;

import com.tj.rxjavaretrofitmvpdemo.base.BasePresenter;
import com.tj.rxjavaretrofitmvpdemo.http.LzyCallback;
import com.tj.rxjavaretrofitmvpdemo.modle.UserModle;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.UserInfo;
import com.tj.rxjavaretrofitmvpdemo.view.UserView;

/**
 * @作者: TJ
 * @时间: 2018/8/2 16:53
 * @描述: 用户相关业务以及逻辑，此类可以复用，只用于用户相关，可以扩充方法分别精选处理
 */
public class UserPresenter extends BasePresenter<UserView, UserModle> {

    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected UserModle createModel() {
        return new UserModle();
    }


    /**
     * 获取数据
     *
     * @param id
     */
    public void getUserData(long id) {
        mModel.getUserData(id, new LzyCallback<UserInfo>() {
            @Override
            public void onNext(UserInfo userInfo) {
                mView.setData(userInfo);
            }
        });
    }
}
