package com.sanyue.jetpakcdemonew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.dataBinding.DataBindActivity;
import com.sanyue.jetpakcdemonew.databinding.ADataBinding;
import com.sanyue.jetpakcdemonew.databinding.ActivityDemoBinding;
import com.sanyue.jetpakcdemonew.databinding.ActivityMainBinding;
import com.sanyue.jetpakcdemonew.liveData.LiveDataActivity;
import com.sanyue.jetpakcdemonew.liveData.LiveDataViewModel;
import com.sanyue.jetpakcdemonew.liveDataBinding.TwoWayActivity;
import com.sanyue.jetpakcdemonew.liveDataBinding.two.DemoActivity;
import com.sanyue.jetpakcdemonew.mvvm.MvvmMainActivity;
import com.sanyue.jetpakcdemonew.viewModel.ViewModelActivity;

public class MainActivity extends AppCompatActivity {
  private  MainViewModel mainViewModel;
  private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //二选一
//        方式1
//          binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //方式2
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider((MyApplication) getApplication()).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);
        getLifecycle().addObserver(mainViewModel);
        Log.e("MainActivity",mainViewModel.text);
        mainViewModel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("MainActivity",s+"");
                binding.text.setText(s);
            }
        });
        binding.ViewModel.setOnClickListener(v->{
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启ViewModel");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, ViewModelActivity.class));
        });
        binding.liveData.setOnClickListener(v -> {
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启LiveData");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, LiveDataActivity.class));
        });
        binding.dataBinding.setOnClickListener(v -> {
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启DataBinding");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, DataBindActivity.class));
        });

        binding.liveDataBinding.setOnClickListener(view -> {
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启双向绑定");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, TwoWayActivity.class));
        });
        binding.mvvm.setOnClickListener(view -> {
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启MVVM");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, MvvmMainActivity.class));
        });
        binding.demo.setOnClickListener(view -> {
            User user = mainViewModel.liveDataName.getValue();
            user.setName("开启DEMO");
            mainViewModel.liveDataName.setValue(user);
            mainViewModel.add();
            startActivity(new Intent(MainActivity.this, DemoActivity.class));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binding!=null){
            binding.unbind();
        }
    }
}