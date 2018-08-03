package com.tj.rxjavaretrofitmvpdemo.base;


import android.content.Context;


/**
 * @作者: TJ
 * @时间: 2018/4/11 11:53
 * @描述: Presenter基类，连接V层和M层，主要处理业务以及逻辑相关的东西，将相关数据传给M层进行网络请求、数据处理，然后将M层返回的数据传递给V层，设置视图
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    public final String TAG = this.getClass().getSimpleName();

    protected V       mView;
    protected M       mModel;
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    public void attachView(V view) {
        mView = view;
        mModel = createModel();
        onViewAttach();
    }


    /**
     * 将Presenter与View解除 Presenter与Model解除
     */
    public void detachView() {
        mView = null;
        mModel = null;
        onViewDetach();
    }

    /**
     * 当View与Presenter绑定时回调，此时界面控件等均未初始化
     */
    protected void onViewAttach() {
    }


    /**
     * 当View与Presenter解除绑定时回调
     */
    protected void onViewDetach() {
    }

    /**
     * 创建model
     *
     * @return
     */
    protected abstract M createModel();

}
