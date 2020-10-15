package com.sanyue.jetpakcdemonew;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableShort;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class MainViewModel extends ViewModel implements LifecycleObserver {
    public String text;
    public MutableLiveData<User> liveDataName = new MutableLiveData<>();
    public LiveData<String> name;
    public ObservableInt count;

    public MainViewModel() {
        count  = new ObservableInt(0);
        name = Transformations.map(liveDataName, new Function<User, String>() {
            @Override
            public String apply(User input) {
                return input.getName();
            }
        });
    }

    public MainViewModel(String name,int age) {
        this();
        User user = new User();
        user.setName(name);
        user.setAge(18);
        liveDataName.setValue(user);
        text = name;
    }

    public void setName(String name){
        User user = new User();
        user.setName(name);
        user.setAge(18);
        liveDataName.setValue(user);
        text = name;
    }

    public void  add(){
        count.set(count.get()+1);
    }

//    private Lifecycle lifecycle;
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private  void activityOnCreate(){
        Log.e("MyObserve","activityOnCreate");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private  void activityOnStart(){
        Log.e("MyObserve","activityOnStart");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private  void activityOnDestroy(){
        Log.e("MyObserve","activityOnDestroy");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private  void activityOnPause(){
        Log.e("MyObserve","activityOnPause");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private  void activityOnStop(){
        Log.e("MyObserve","activityOnPause");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private  void activityOnResume(){
        Log.e("MyObserve","activityOnResume");
    }
//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    private  void activityOnAny(){
//        Log.e("MyObserve","activityOnAny当前状态"+lifecycle.getCurrentState());
//    }

}
