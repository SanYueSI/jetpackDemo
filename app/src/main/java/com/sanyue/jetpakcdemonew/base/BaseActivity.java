package com.sanyue.jetpakcdemonew.base;

import android.content.ContentProvider;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ViewDataBinding dataBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里是activity中使用 dataBinding 我这里就是简单的写了个抽象类节省下事情
        dataBinding = DataBindingUtil.setContentView(this,getLayout());
        init();
    }
    public abstract void init();
    public abstract int getLayout();
    protected  <T extends ViewDataBinding>  T getViewDataBinding() {
        return (T) dataBinding;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dataBinding!=null){
            dataBinding.unbind();
        }
    }
}
