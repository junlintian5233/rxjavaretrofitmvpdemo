package com.tj.rxjavaretrofitmvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tj.rxjavaretrofitmvpdemo.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * @作者: TJ
 * @时间: 2018/7/23 16:56
 * @描述: Activity基类，MVP中的V层，主要设置视图相关
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener, BaseView {

    public final String TAG = this.getClass().getSimpleName();

    protected BaseActivity mContext;
    /**
     * presenter
     */
    protected P            mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //创建presenter并绑定view，这里默认创建一个Presenter，如果需要创建多个需要自己处理。
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
        setContentView(onSetLayoutId());
        init();
    }

    /**
     * 设置布局文件
     *
     * @return 返回布局文件资源Id
     */
    protected abstract int onSetLayoutId();

    /**
     * 创建Presenter
     *
     * @return 返回Presenter
     */
    protected abstract P createPresenter();


    /**
     * 初始化页面
     */
    protected void init() {
        initView();
        bindEvent();
        initData();
    }


    /**
     * 初始化view
     */
    public abstract void initView();


    /**
     * 绑定事件
     */
    public void bindEvent() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 显示一个Toast信息
     */
    @Override
    public void showToastMessage(String message) {
        if (message != null) {
            ToastUtils.showShort(message);
        }
    }

    @Override
    public void showToastMessage(int res) {
        if (res != 0) {
            ToastUtils.showShort(getString(res));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
