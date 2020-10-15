package com.sanyue.jetpakcdemonew.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class MyViewModel extends ViewModel {
    private User user;
    private int count;
    private MediatorLiveData<String> stringMediatorLiveData;
    public LiveData<String> stringLiveData;

    public MyViewModel() {
        stringMediatorLiveData = new MediatorLiveData<>();
        stringLiveData = stringMediatorLiveData;
        this.user = new User("张三",11);
        this.count = 13;
    }

    public void update(String s){
        stringMediatorLiveData.setValue(s);
    }

    public MyViewModel(User user, int count) {
        this.user = user;
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
