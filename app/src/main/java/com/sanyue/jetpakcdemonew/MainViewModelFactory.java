package com.sanyue.jetpakcdemonew;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {
    private String name;
    private int age;

    public MainViewModelFactory(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(name,age);
    }
}
