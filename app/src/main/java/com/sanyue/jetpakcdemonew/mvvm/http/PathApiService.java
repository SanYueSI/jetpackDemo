package com.sanyue.jetpakcdemonew.mvvm.http;

import androidx.databinding.ObservableField;

import com.sanyue.jetpakcdemonew.mvvm.bean.WanAndroidNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/***
 * Create by Yip
 * Create Time 2020/9/28
 */
public interface PathApiService {
    //https://www.wanandroid.com/article/list/0/json
    @GET("article/list/{index}/json")
    Observable<WanAndroidNewsBean> getNes(@Path("index")int index);

}
