package com.tj.rxjavaretrofitmvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @作者: TJ
 * @时间: 2018/7/24 10:57
 * @描述: Fregment基类，MVP中的V层，主要设置视图相关
 */
public abstract class BaseFregment<P extends BasePresenter> extends Fragment implements BaseView {

    public final String TAG          = this.getClass().getSimpleName();
    public       View   mContentView = null;

    public BaseActivity mContext;
    public P            mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) this.getActivity();
        //创建Presenter并绑定view
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(onSetLayoutId(), container, false);
        }
        ButterKnife.bind(this, mContentView);
        init();
        return mContentView;
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract P createPresenter();


    /**
     * 设置布局文件
     *
     * @return 返回布局文件资源Id
     */
    public abstract int onSetLayoutId();

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
    public void showToastMessage(String message) {
        mContext.showToastMessage(message);
    }

    @Override
    public void showToastMessage(int res) {
        mContext.showToastMessage(res);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        mContentView = null;
    }
}
