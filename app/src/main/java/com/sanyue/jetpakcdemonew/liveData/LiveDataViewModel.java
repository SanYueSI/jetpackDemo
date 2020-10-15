package com.sanyue.jetpakcdemonew.liveData;

import android.os.Looper;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;

import java.util.ArrayList;
import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class LiveDataViewModel extends ViewModel {
    public MutableLiveData<User> userMutableLiveData;
    public LiveData<Integer> age;
    public LiveData<User> userLiveData;
    private MutableLiveData<Integer> userIndex = new MutableLiveData<>();
    public MutableLiveData<Integer> time = new MutableLiveData<>();

    private List<User> users;
    public LiveDataViewModel() {
        time.setValue(0);
        users = new ArrayList<>();
        users.add(new User("李四",16));
        users.add(new User("张三",18));
        users.add(new User("王武",17));
        users.add(new User("钱六",20));
        userMutableLiveData = new MutableLiveData<>();
        
        age = Transformations.map(userMutableLiveData, new Function<User, Integer>() {
            @Override
            public Integer apply(User input) {
                return input.getAge();
            }
        });
        userLiveData =  Transformations.switchMap(userIndex, new Function<Integer, LiveData<User>>() {
            @Override
            public LiveData<User> apply(Integer input) {
                return getUser(input);
            }
        });
    }

    public void addUser(User user){
        //这里值得注意的是setValue主线程用没问题 如果是子线程那就要用postValue
        // userMutableLiveData.postValue(user);
        if(Looper.myLooper() == Looper.getMainLooper()){
            userMutableLiveData.setValue(user);
        }else {
            userMutableLiveData.postValue(user);
        }
    }
    public void queryUser(int userId){
        userIndex.setValue(userId);
    }
    //模拟查询
    public  LiveData<User> getUser(int userId){
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        if(userId>users.size()-1){
            userMutableLiveData.setValue(new User("没有该用户",0));
            return userMutableLiveData;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userMutableLiveData.postValue(users.get(userId));
            }
        }).start();
        return userMutableLiveData;
    }
}
