package com.sanyue.jetpakcdemonew.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.databinding.ActivityMvvmMainBinding;
import com.sanyue.jetpakcdemonew.mvvm.bean.WanAndroidNewsBean;
import com.sanyue.jetpakcdemonew.mvvm.http.State;

import java.util.List;


public class MvvmMainActivity extends BaseActivity {
    private MvvMViewmodel viewmodel;
    private ActivityMvvmMainBinding activityMvvmMainBinding;
    private RecycleAdapter recycleAdapter;
    private Dialog diglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        diglog = MyDiglog.createLoadingDialog(this,"请求中");
        activityMvvmMainBinding= getViewDataBinding();
        viewmodel = new ViewModelProvider(this).get(MvvMViewmodel.class);
        diglog.show();
        activityMvvmMainBinding.setLifecycleOwner(this);
        viewmodel.getPageUpData();
        activityMvvmMainBinding.setViewModel(viewmodel);
        activityMvvmMainBinding.recycle.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(viewmodel.list);

        activityMvvmMainBinding.recycle.setAdapter(recycleAdapter);
        viewmodel.listLiveData.observe(this, new Observer<List<WanAndroidNewsBean.ItemDetailsBean>>() {
            @Override
            public void onChanged(List<WanAndroidNewsBean.ItemDetailsBean> itemDetailsBeans) {
             viewmodel.list.clear();
             viewmodel.list.addAll(itemDetailsBeans);
             recycleAdapter.notifyDataSetChanged();
            }
        });

        viewmodel.state.observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                diglog.dismiss();
                switch (state.getCode()){
                    case ERROR:
                        Toast.makeText(MvvmMainActivity.this,state.getMsg(),Toast.LENGTH_LONG).show();
                        break;
                    case LOADING:
                        diglog.show();
                        break;

                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_mvvm_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(diglog!=null){
            diglog.dismiss();
        }
        diglog= null;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.page_up:
//                viewmodel.getPageUpData();
//                break;
//            case R.id.page_down:
//                viewmodel.getPageNextData();
//                break;
//        }
//    }
}