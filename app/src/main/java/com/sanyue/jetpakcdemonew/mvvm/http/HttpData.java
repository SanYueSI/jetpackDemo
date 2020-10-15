package com.sanyue.jetpakcdemonew.mvvm.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * Create by Yip
 * Create Time 2020/9/28
 */
public class HttpData {
    private static  Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit  = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build())
                    .build();
        }
        return retrofit;
    }
}
