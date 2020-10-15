package com.sanyue.jetpakcdemonew.liveData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ActivityLiveDataBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * */
public class LiveDataActivity extends BaseActivity {
   private ActivityLiveDataBinding dataBinding;
   private LiveDataViewModel model;
   private  MyFragment fragment;
   private String string;
   private Timer timer;
    private TimerTask task;
    @Override
    public void init() {
        string = "onCreate";
        dataBinding =  getViewDataBinding();
        fragment  = new MyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout,fragment).commit();
        getSupportFragmentManager().beginTransaction().show(fragment);
        model = new ViewModelProvider(this).get(LiveDataViewModel.class);
        model.age.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                dataBinding.tvAge.setText("年龄:"+integer);
            }
        });
        model.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                dataBinding.name.setText("名字:"+user.getName());
            }
        });
        model.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                dataBinding.name.setText("名字:"+user.getName());
                dataBinding.tvAge.setText("年龄:"+user.getAge());

            }
        });

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                model.time.postValue(model.time.getValue()+1);
            }
        };

        model.time.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.e("LiveDataActivity",string+"--->"+integer);
            }
        });

        model.time.observeForever(integerObserver);
    }

    Observer<Integer> integerObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            Log.e("LiveDataActivity",string+"--->"+integer);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        string = "onDestroy";
        Log.e("LiveDataActivity","onDestroy");
        task.cancel();
        model.time.removeObserver(integerObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        string = "onStop";
        Log.e("LiveDataActivity","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        string = "onStart";
        Log.e("LiveDataActivity","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        string = "onResume";
        Log.e("LiveDataActivity","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        string = "onPause";
        Log.e("LiveDataActivity","onPause");

    }

    public void addUser(View view){
        User user  = new User();
        user.setName("张三");
        user.setAge(18);
        model.addUser(user);
        timer.schedule(task, 1000, 1000);
    }

    public void query(View view){
        model.queryUser(Integer.parseInt(dataBinding.editQuery.getText().toString()));
    }
    @Override
    public int getLayout() {
        return R.layout.activity_live_data;
    }

}