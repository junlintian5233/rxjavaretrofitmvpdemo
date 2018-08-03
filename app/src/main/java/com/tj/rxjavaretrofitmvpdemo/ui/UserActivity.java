package com.tj.rxjavaretrofitmvpdemo.ui;

import android.widget.TextView;

import com.tj.rxjavaretrofitmvpdemo.R;
import com.tj.rxjavaretrofitmvpdemo.base.BaseActivity;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.UserInfo;
import com.tj.rxjavaretrofitmvpdemo.presenter.UserPresenter;
import com.tj.rxjavaretrofitmvpdemo.view.UserView;

import butterknife.Bind;

public class UserActivity extends BaseActivity<UserPresenter> implements UserView {

    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_gender)
    TextView mTvGender;

    @Override
    protected int onSetLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(mContext);
    }

    @Override
    public void initView() {
        //初始化一些UI
    }

    @Override
    protected void initData() {
        //请求用户数据
        mPresenter.getUserData(1100);
    }

    /**
     * 由MainPresenter回调过来的数据，然后更新UI
     *
     * @param userInfo
     */
    @Override
    public void setData(UserInfo userInfo) {
        //更新UI
        mTvName.setText(userInfo.getName());
        mTvGender.setText(userInfo.getGender());
    }

}
