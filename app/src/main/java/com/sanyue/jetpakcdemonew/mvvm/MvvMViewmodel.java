package com.sanyue.jetpakcdemonew.mvvm;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.mvvm.bean.WanAndroidNewsBean;
import com.sanyue.jetpakcdemonew.mvvm.http.HttpData;
import com.sanyue.jetpakcdemonew.mvvm.http.PathApiService;
import com.sanyue.jetpakcdemonew.mvvm.http.State;
import com.sanyue.jetpakcdemonew.mvvm.http.StateType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/***
 * Create by Yip
 * Create Time 2020/9/29
 */
public class MvvMViewmodel extends ViewModel {
    public MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<State> state = new MutableLiveData<>();
    public LiveData<List<WanAndroidNewsBean.ItemDetailsBean>> listLiveData;
    private  int page =0;
    public  List<WanAndroidNewsBean.ItemDetailsBean> list = new ArrayList<>();
    public CompositeDisposable dis;


    public MvvMViewmodel() {
        dis = new CompositeDisposable();
        listLiveData = Transformations.switchMap(mutableLiveData, new Function<Integer, LiveData<List<WanAndroidNewsBean.ItemDetailsBean>>>() {
            @Override
            public LiveData<List<WanAndroidNewsBean.ItemDetailsBean>> apply(Integer input) {
                return getData(input);
            }
        });

    }
    public void getPageUpData(){
       mutableLiveData.setValue(page>0?page-1:page);
    }
    public void getPageNextData(){
        mutableLiveData.setValue(page+1);
    }

    private LiveData<List<WanAndroidNewsBean.ItemDetailsBean>> getData(int page){
        MediatorLiveData<List<WanAndroidNewsBean.ItemDetailsBean>> mediatorLiveData = new MediatorLiveData<>();
        HttpData.getRetrofit().create(PathApiService.class).getNes(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanAndroidNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        state.setValue(new State(StateType.LOADING,""));
                        dis.add(d);
                    }

                    @Override
                    public void onNext(WanAndroidNewsBean wanAndroidNewsBean) {
                        state.setValue(new State(StateType.SUCCESS,""));
                        mediatorLiveData.setValue(wanAndroidNewsBean.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        state.setValue(new State(StateType.ERROR,e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAGSS","onComplete");
                    }
                });
        return mediatorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(dis!=null){
            dis.dispose();
        }
    }
}
