package com.sanyue.jetpakcdemonew.dataBinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;

import java.util.ArrayList;
import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/25
 */
public class ActivityModel extends ViewModel {
    private User user;
    private List<User> mutableLiveData;

    public User getUser() {
        return user;
    }

    public ActivityModel() {
        mutableLiveData = new ArrayList<>();
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addUser(User user){
        mutableLiveData.add(user);
    }

    public List<User> getMutableLiveData() {
        return mutableLiveData;
    }

    public String getUserInfo(){
        return user==null?"还没得个人呢":user.getName()+"今年"+user.getAge()+"岁了";
    }
}
