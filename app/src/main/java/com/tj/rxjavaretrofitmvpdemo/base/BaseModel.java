package com.tj.rxjavaretrofitmvpdemo.base;

import com.tj.rxjavaretrofitmvpdemo.http.LzyCallback;
import com.tj.rxjavaretrofitmvpdemo.http.RetrofitApi;
import com.tj.rxjavaretrofitmvpdemo.http.RetrofitApiService;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.HttpResult;
import com.tj.rxjavaretrofitmvpdemo.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者: TJ
 * @时间: 2018/7/24 9:26
 * @描述: Model基类，MVP中M层，对应P层，主要获取数据以及处理数据，然后返回给P层
 */
public class BaseModel {

    public final String TAG = this.getClass().getSimpleName();
    protected       CompositeDisposable mCompositeDisposable;
    protected final RetrofitApiService  mApiService;


    public BaseModel() {
        //Presenter在创建Model对象的时候创建CompositeDisposable对象，在Presenter解除与View绑定的时候，取消所有的订阅。
        mCompositeDisposable = new CompositeDisposable();
        //初始化retrofit，给之后的网络请求做准备
        mApiService = RetrofitApi.getService();
    }


    //将每次的订阅操作进行封装，简化重复代码量，分割返回的数据
    public <T> Observable<T> applySchedulers(Observable<HttpResult<T>> o) {
        Observable<T> observable = o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<HttpResult<T>, T>() {
                    @Override
                    public T apply(HttpResult<T> result) throws Exception {
                        return result.getData();
                    }
                });
        return observable;
    }


    /**
     * 创建观察者  这里对观察着过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param <T>
     * @return
     */
    public <T> Observer<T> newObserver(final LzyCallback callback) {
        return new Observer<T>() {

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, "newObserver-> onError: ---" + e.getMessage());
                callback.onError(e);
            }

            @Override
            public void onComplete() {
                LogUtils.e(TAG, "newObserver-> onComplete: ---");
                callback.onComplete();
            }

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeDisposable.isDisposed()) {
                    LogUtils.e(TAG, "newObserver-> onNext: ---");
                    callback.onNext(t);
                }
            }
        };
    }
}
