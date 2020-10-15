package com.sanyue.jetpakcdemonew.dataBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ItemAdapterBinding;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/25
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<User> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //二选一
        ItemAdapterBinding itemAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_adapter,parent,false);
//        ItemAdapterBining itemAdapterBinding = ItemAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(itemAdapterBinding);
    }

    public MyAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemAdapterBinding.setUser(users.get(position));
        //当可变或可观察对象发生更改时，绑定会按照计划在下一帧之前发生更改。但有时必须立即执行绑定。要强制执行，请使用 executePendingBindings() 方法。
        //这句话很关键 不加数据会错乱 https://developer.android.google.cn/topic/libraries/data-binding/generated-binding
        holder.itemAdapterBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private  ItemAdapterBinding itemAdapterBinding;

        public ViewHolder(@NonNull ItemAdapterBinding itemAdapterBinding) {
            super(itemAdapterBinding.getRoot());
            this.itemAdapterBinding = itemAdapterBinding;
        }
    }
}
