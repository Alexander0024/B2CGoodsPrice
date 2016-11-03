package com.alexsophia.b2cgoodsprice.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiManager
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public class ApiManager {
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiInterface mApiInterface;

    private ApiManager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mApiInterface = mRetrofit.create(ApiInterface.class);
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    /**
     * 获取单例
     */
    public static ApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
