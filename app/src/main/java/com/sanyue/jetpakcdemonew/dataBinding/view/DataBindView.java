package com.sanyue.jetpakcdemonew.dataBinding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ViewDataBindingBinding;

/***
 * Create by Yip
 * Create Time 2020/9/25
 * 组合控件databinding的用法
 */
public class DataBindView extends LinearLayout {
    private ViewDataBindingBinding viewDataBinding;
    public DataBindView(Context context) {
        this(context,null);
    }

    public DataBindView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DataBindView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //在组合控件这么绑定 二选一
//        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.view_data_binding,this,true);
        viewDataBinding = ViewDataBindingBinding.inflate(LayoutInflater.from(context),this,true);

    }

    public void setData(User user){
        viewDataBinding.setUser(user);
    }
}
