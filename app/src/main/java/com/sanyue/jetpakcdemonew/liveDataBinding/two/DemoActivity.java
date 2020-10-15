package com.sanyue.jetpakcdemonew.liveDataBinding.two;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ActivityDemoBinding;
import com.sanyue.jetpakcdemonew.liveDataBinding.Book;
import com.sanyue.jetpakcdemonew.liveDataBinding.ListAdapter;

public class DemoActivity extends BaseActivity implements View.OnClickListener {
    private DemoViewModel viewModel;
    private ActivityDemoBinding demoBinding;
    private Book book;
    private ObservableBoolean a =  new ObservableBoolean(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DemoViewModel.class);
        demoBinding = getViewDataBinding();
        demoBinding.setMode(viewModel);
        demoBinding.setOnclick(this);
        demoBinding.setLifecycleOwner(this);
        ListAdapter adapter = new ListAdapter(viewModel.list);
        demoBinding.setAdapter(adapter);
        demoBinding.setLayout(new LinearLayoutManager(this));
        DemoRecycleAdapter recycleAdapter = new DemoRecycleAdapter(viewModel.list);
        demoBinding.setRecycleAdapter(recycleAdapter);
        demoBinding.setIsA(a);


    }

    @Override
    public void init() {
    }

    @Override
    public int getLayout() {
        return R.layout.activity_demo;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
          case   R.id.addUser:

              viewModel.addUser(new User("张三",18));
            break;
            case R.id.countAdd:
                viewModel.add();
                break;
            case R.id.addBook:
                viewModel.addBooK("Android入门到放弃",999);
                break;
            case R.id.test:
                if(a.get()){
                    a.set(false);
                }else {
                    a.set(true);
                }
                break;
        }
    }
}