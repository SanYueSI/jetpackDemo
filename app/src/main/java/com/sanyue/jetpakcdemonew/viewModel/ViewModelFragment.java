package com.sanyue.jetpakcdemonew.viewModel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.liveData.LiveDataViewModel;

/***
 * Create by Yip
 * Create Time 2020/9/24
 */
public class ViewModelFragment extends Fragment {
    private TextView rootView;
    private MyViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (TextView) inflater.inflate(R.layout.fragment_view_model,container,false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(MyViewModel.class);

        if(viewModel.getUser()!=null){
            rootView.setText(viewModel.getUser().getName()+"今年"+viewModel.getUser().getAge()+"岁");
        }
        return rootView;
    }


}
