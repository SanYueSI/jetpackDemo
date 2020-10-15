package com.sanyue.jetpakcdemonew.viewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sanyue.jetpakcdemonew.MainViewModelFactory;
import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.bean.User;

/**
 * https://developer.android.google.cn/topic/libraries/architecture/viewmodel
 *ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据。ViewModel 类让数据可在发生屏幕旋转等配置更改后继续留存
 *
 * viewModel 想在被在创建的时候传入参数进行初始化 重新写一个类实现 ViewModelProvider.Factory这个接口
 *  参考 MainViewModelFactory.java
 *  在viewmodel实例化的时候     mainViewModel = new ViewModelProvider(this,new MainViewModelFactory("hello jetPack!",18)).get(MainViewModel.class);
 */
public class ViewModelActivity extends BaseActivity {
    private MyViewModel viewModel;
    private TextView textView;
    private ViewModelFragment fragment;

    @Override
    public void init() {
        textView = findViewById(R.id.text);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.stringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("MainActivity",s+"----");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.update("哈哈哈哈");
            }
        });

        if(viewModel.getUser()!=null){
            refreshText();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_view_model;
    }
    public void addUser(View view){
        User user  = new User();
        user.setName("张三");
        user.setAge(18);
        viewModel.setUser(user);
        refreshText();
    }
    public void showFragment(View view){
        fragment = new ViewModelFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout,fragment).commit();
        fragmentTransaction.show(fragment);
    }

    public void refreshText(){
        textView.setText(viewModel.getUser().getName()+"今年"+viewModel.getUser().getAge()+"岁");
    }
}