package com.sanyue.jetpakcdemonew.liveDataBinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.databinding.ItemListViewBinding;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/27
 */
public class ListAdapter extends BaseAdapter {
    private ObservableArrayList<String> list;

    public ListAdapter(ObservableArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemListViewBinding binding = null;
        if(binding==null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.item_list_view,viewGroup,false);
        }
        binding.setStr(list.get(i));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
