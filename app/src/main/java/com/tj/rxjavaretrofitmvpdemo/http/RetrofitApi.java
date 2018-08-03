package com.tj.rxjavaretrofitmvpdemo.http;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @作者: TJ
 * @时间: 2018/7/23 16:23
 * @描述: Retrofit相关设置
 */
public class RetrofitApi {

    // 请求公共部分
    public static final String BASE_URL = "https://api.jdvip.com/";

    private static RetrofitApiService service;
    private static Retrofit           retrofit;

    public static RetrofitApiService getService() {
        if (service == null) {
            service = getRetrofit().create(RetrofitApiService.class);
        }
        return service;
    }

    /**
     * 拦截器  给所有的请求添加消息头
     */
    private static Interceptor mInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "*/*")
                    .addHeader("X-HB-Client-Type", "ayb-android")
                    .addHeader("Content-Type", "multipart/form-data; boundary=7db372eb000e2")
                    .build();
            return chain.proceed(request);
        }
    };

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            // log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)// 15秒连接超时
                    .addInterceptor(interceptor)
                    .addInterceptor(mInterceptor)
                    .build();

            GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

            retrofit = new Retrofit.Builder()
                    // 使用OkHttp Client
                    .client(client)
                    // baseUrl总是以/结束，@URL不要以/开头
                    .baseUrl(BASE_URL)
                    // 集成Gson转换器
                    .addConverterFactory(factory)
                    // 集成RxJava处理
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
