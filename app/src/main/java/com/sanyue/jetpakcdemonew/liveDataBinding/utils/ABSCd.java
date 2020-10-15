package com.sanyue.jetpakcdemonew.liveDataBinding.utils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sanyue.jetpakcdemonew.BR;

/***
 * Create by Yip
 * Create Time 2020/10/14
 */
public class ABSCd extends BaseObservable {
    private int subMajorCount;
    private boolean isAttention;
    private int majorType;

    public int getSubMajorCount() {
        return subMajorCount;
    }

    public void setSubMajorCount(int subMajorCount) {
        this.subMajorCount = subMajorCount;
    }
    @Bindable
    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
        notifyPropertyChanged(BR.attention);
    }

    public int getMajorType() {
        return majorType;
    }

    public void setMajorType(int majorType) {
        this.majorType = majorType;
    }
}
