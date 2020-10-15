package com.sanyue.jetpakcdemonew.liveDataBinding.two;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ItemDemoRecyViewBinding;
import com.sanyue.jetpakcdemonew.databinding.ItemRecyViewBinding;
import com.sanyue.jetpakcdemonew.liveDataBinding.Book;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/27
 */
public class DemoRecycleAdapter extends RecyclerView.Adapter<DemoRecycleAdapter.ViewHolder> {
    private List<String> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemDemoRecyViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public DemoRecycleAdapter(ObservableArrayList<String> users) {
        this.users = users;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemAdapterBinding.setUser(users.get(position));
        //这句话很关键 不加数据会错乱 https://developer.android.google.cn/topic/libraries/data-binding/generated-binding
        holder.itemAdapterBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDemoRecyViewBinding itemAdapterBinding;
        public ViewHolder(@NonNull ItemDemoRecyViewBinding itemAdapterBinding) {
            super(itemAdapterBinding.getRoot());
            this.itemAdapterBinding = itemAdapterBinding;
        }
    }
}