package com.sanyue.jetpakcdemonew.liveData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.bean.User;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class MyFragment extends Fragment {
    private TextView rootView;
    private LiveDataViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (TextView) inflater.inflate(R.layout.fragment_view_model,container,false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(LiveDataViewModel.class);
        viewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        viewModel.userMutableLiveData.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                rootView.setText("新增了个学生"+user.getName()+"今年"+user.getAge());
            }
        });
        viewModel.userLiveData.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                rootView.setText("查询结果-"+user.getName()+"今年"+user.getAge());
            }
        });
        return rootView;
    }
}
