package com.sanyue.jetpakcdemonew;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class MyApplication extends Application implements ViewModelStoreOwner {
    private static ViewModelStore viewModelStore;
    private MainViewModel mainViewModel;
    public static ViewModelStoreOwner viewModelStoreOwner;

    @Override
    public void onCreate() {
        super.onCreate();
        viewModelStore = new ViewModelStore();
        mainViewModel = new ViewModelProvider(this,new MainViewModelFactory("hello jetPack!",18)).get(MainViewModel.class);
        viewModelStoreOwner = this;
    }

    public static ViewModelStoreOwner getViewModelStoreOwner() {
        return viewModelStoreOwner;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }
}
