package com.sanyue.jetpakcdemonew.liveDataBinding.utils;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.sanyue.jetpakcdemonew.R;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/25
 */

/**
 * https://developer.android.google.cn/topic/libraries/data-binding/binding-adapters
 */

/**
 * 这里实现下listView绑定适配器那样的效果
 */
public class BindingAdapters {
    /**
     * @BindingAdapter的关键就是绑定一个liveData的数据进行关联当数据进行了改变那么会再次调用该方法
     *
     * @param view
     * @param position 这个是最关键的 绑定这个的值必须得是liveData类型可以观察的
     *                 如果是普通的listView 那么这个方法在数据进行改变的时候就不会在执行了
     * @param adapter
     */
    @BindingAdapter({"android:scrollToPosition", "android:adapter"})
    public static void setRecycleViewAdapter(RecyclerView view,int position,RecyclerView.Adapter adapter){
        if (view.getAdapter() == null) {
            view.setAdapter(adapter);
        }else {
            view.getAdapter().notifyDataSetChanged();
        }
        view.scrollToPosition(position);
    }
    @BindingAdapter("android:layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,RecyclerView.LayoutManager LayoutManager){
      recyclerView.setLayoutManager(LayoutManager);
    }

    //如果您希望在设置了任意属性时调用适配器，则可以将适配器的可选 requireAll 标志设置为 false
    @BindingAdapter(value={"android:imageUrl", "android:placeholder"}, requireAll=false)
    public static void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
        if (url == null) {
            imageView.setImageDrawable(placeHolder);
        } else {
//            MyImageLoader.loadInto(imageView, url, placeholder);
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }
    @BindingAdapter(value = {"android:ScrollStateChanged","android:onScrolled"},requireAll = false)
    public static void setScroll(RecyclerView view,BindingAdapters.OnScrollStateChanged changed,BindingAdapters.OnScrolled onScrolled){
        RecyclerView.OnScrollListener listener =null;
        if(changed!=null||onScrolled!=null){
            listener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(changed!=null){
                        changed.onScrollStateChanged(recyclerView,newState);
                    }
                }
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(onScrolled!=null){
                        onScrolled.onScrolled(recyclerView,dx,dy);
                    }
                }
            };
        }
        if(listener!=null){
            view.addOnScrollListener(listener);
        }
    }

    public  interface OnScrollStateChanged{
        void onScrollStateChanged(RecyclerView recyclerView, int newState);
    }
    public  interface OnScrolled{
         void onScrolled( RecyclerView recyclerView, int dx, int dy);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view,@DrawableRes int resId) {
        view.setImageResource(resId);
    }
}
