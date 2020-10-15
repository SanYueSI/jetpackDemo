package com.sanyue.jetpakcdemonew.dataBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.FragmentDataBindingBinding;

/***
 * Create by Yip
 * Create Time 2020/9/25
 */
public class DataBindFragment extends Fragment {
   private FragmentDataBindingBinding dataBindFragment;
   private ActivityModel model;
    private MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //二选一
        //        dataBindFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_data_binding,container,false);
        dataBindFragment = FragmentDataBindingBinding.inflate(inflater, container, false);

        model = new ViewModelProvider(getActivity()).get(ActivityModel.class);
        dataBindFragment.setMode(model);
        dataBindFragment.myView.setData(model.getUser());
        adapter = new MyAdapter(model.getMutableLiveData());
        dataBindFragment.recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBindFragment.recycle.setAdapter(adapter);
        dataBindFragment.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.addUser(new User(model.getMutableLiveData().size()+"-->",model.getMutableLiveData().size()));
                adapter.notifyDataSetChanged();
                dataBindFragment.recycle.scrollToPosition(adapter.getItemCount()-1);
            }
        });
        return dataBindFragment.getRoot();
    }
}
