package com.sanyue.jetpakcdemonew.mvvm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.sanyue.jetpakcdemonew.databinding.ItemRecyViewBinding;
import com.sanyue.jetpakcdemonew.databinding.ItemRecycleViewBinding;
import com.sanyue.jetpakcdemonew.liveDataBinding.Book;
import com.sanyue.jetpakcdemonew.mvvm.bean.WanAndroidNewsBean;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/27
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<WanAndroidNewsBean.ItemDetailsBean> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemRecycleViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public RecycleAdapter(List<WanAndroidNewsBean.ItemDetailsBean> users) {
        this.users = users;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemAdapterBinding.setBean(users.get(position));
        //这句话很关键 不加数据会错乱 https://developer.android.google.cn/topic/libraries/data-binding/generated-binding
        holder.itemAdapterBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecycleViewBinding itemAdapterBinding;
        public ViewHolder(@NonNull ItemRecycleViewBinding itemAdapterBinding) {
            super(itemAdapterBinding.getRoot());
            this.itemAdapterBinding = itemAdapterBinding;
        }
    }
}