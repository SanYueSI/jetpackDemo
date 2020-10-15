package com.sanyue.jetpakcdemonew.liveDataBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.dataBinding.MyAdapter;
import com.sanyue.jetpakcdemonew.databinding.ItemAdapterBinding;
import com.sanyue.jetpakcdemonew.databinding.ItemRecyViewBinding;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/27
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private ObservableArrayList<Book> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemRecyViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public RecycleAdapter(ObservableArrayList<Book> users) {
        this.users = users;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemAdapterBinding.setBook(users.get(position));
        //这句话很关键 不加数据会错乱 https://developer.android.google.cn/topic/libraries/data-binding/generated-binding
        holder.itemAdapterBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ItemRecyViewBinding itemAdapterBinding;
        public ViewHolder(@NonNull ItemRecyViewBinding itemAdapterBinding) {
            super(itemAdapterBinding.getRoot());
            this.itemAdapterBinding = itemAdapterBinding;
        }
    }
}