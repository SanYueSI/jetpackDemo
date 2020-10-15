package com.sanyue.jetpakcdemonew.liveDataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sanyue.jetpakcdemonew.BR;

/***
 * Create by Yip
 * Create Time 2020/9/27
 */

/**
 * //https://developer.android.google.cn/topic/libraries/data-binding/observability
 * 关键是 @Bindable 和 notifyPropertyChanged 这俩玩意
 */
public class Book extends BaseObservable {
    private String name;
    private int pages;

    public Book() {
    }

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.score);
    }
    @Bindable
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
        notifyPropertyChanged(BR.pages);
    }
    @Bindable
    public String getScore(){
        if(name==null){
            return "";
        }
        return name.startsWith("Android")?name+"强烈推荐":name+"这破书没啥好看的";
    }
}
