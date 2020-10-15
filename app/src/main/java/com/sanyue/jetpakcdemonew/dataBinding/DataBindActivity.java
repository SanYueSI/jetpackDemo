package com.sanyue.jetpakcdemonew.dataBinding;

import androidx.lifecycle.ViewModelProvider;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ActivityDataBindBinding;

/**
 * https://developer.android.google.cn/topic/libraries/data-binding/start
 * DataBind属于数据绑定 把数据直接塞给xml布局 节省了setxxx的代码 能在xml里更直观的看到view的数据来源
 * activity fragment adapter 以及 组合控件 DataBind的数据绑定是不一样的 相同的是各xml的使用
 * 由layout包裹整个布局 data包裹数据  variable 是设定设定绑定这个xml的数据 可以多个数据来源
 * 在需要绑定数据的view使用@{xxx.变量名} xxx是variable 设置name 注意的是不能访问私有的变量和方法
 * 这里还有个注意点绑定DataBinding的类命名要大写开头 不然编译会报错-Cause: couldn't make a guess for com.xxx.xxx
 *<layout>
 *     <data>
 *         <variable
 *             name="xxx"
 *             type="xxx.xxxx.xxx" />
 *         <variable
 *             name="xxx"
 *             type="xxx.xxxx.xxx" />
 *     </data>
 *<布局>
 *      <TextView
 *         android:id="@+id/count"
 *         android:layout_width="wrap_content"
 *         android:layout_height="wrap_content"
 *         android:text='@{"点击次数"+xxx.count}'
 *         android:hint="点击次数"
 *         android:textSize="18sp" />
 *</布局>
 * </layout>
 *
 * */
public class DataBindActivity extends BaseActivity {
    private ActivityModel model;
    private ActivityDataBindBinding dataBindBinding;
    private DataBindFragment fragment;
    @Override
    public void init() {
        dataBindBinding = getViewDataBinding();
        model  = new ViewModelProvider(this).get(ActivityModel.class);
        model.setUser(new User("张三",18));
        dataBindBinding.setMode(model);
        dataBindBinding.button1.setOnClickListener(view -> {
            fragment = new DataBindFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout, fragment).commit();
            getSupportFragmentManager().beginTransaction().show(fragment);
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_data_bind;
    }
}