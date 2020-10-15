package com.sanyue.jetpakcdemonew.liveDataBinding;

import android.util.Log;

import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableParcelable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;

import java.util.Random;

/***
 * Create by Yip
 * Create Time 2020/9/25
 */
public class TwoWayViewModel extends ViewModel {
    private MediatorLiveData<User> userMediatorLiveData;
    public LiveData<User> userLiveData;
    public ObservableInt carNumber;
    public ObservableBoolean check = new ObservableBoolean(false);
    public ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();
    public ObservableField<User> field = new ObservableField<>();
    public ObservableArrayList<Book> books = new ObservableArrayList<>();
    public TwoWayViewModel() {
        this.carNumber =new ObservableInt(0);
        this.userMediatorLiveData = new MediatorLiveData<>();
        this.userLiveData = userMediatorLiveData;
        check.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                ObservableBoolean observableBoolean  = (ObservableBoolean) sender;
                Log.e("onPropertyChanged",observableBoolean.get()+"");

            }
        });
        observableArrayList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<String>>() {
            @Override
            public void onChanged(ObservableList<String> sender) {
                Log.e("onChanged",sender.size()+"onChanged");
            }

            @Override
            public void onItemRangeChanged(ObservableList<String> sender, int positionStart, int itemCount) {
                Log.e("onChanged",sender.size()+"onItemRangeChanged");

            }

            @Override
            public void onItemRangeInserted(ObservableList<String> sender, int positionStart, int itemCount) {
                Log.e("onChanged",sender.size()+"onItemRangeInserted");

            }

            @Override
            public void onItemRangeMoved(ObservableList<String> sender, int fromPosition, int toPosition, int itemCount) {
                Log.e("onChanged",sender.size()+"onItemRangeMoved");

            }

            @Override
            public void onItemRangeRemoved(ObservableList<String> sender, int positionStart, int itemCount) {
                Log.e("onChanged",sender.size()+"onItemRangeRemoved");

            }
        });
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void addUser(User user){
        field.set(user);
        userMediatorLiveData.setValue(user);
    }
    public String getUser(){
        return userMediatorLiveData.getValue()==null?"毛都没有哦":userMediatorLiveData.getValue().getName()+"今年"+userMediatorLiveData.getValue().getAge();
    }
    public void createNumber(){
        Random random = new Random();
        carNumber.set(random.nextInt(8888)+1111);
        observableArrayList.add(String.valueOf(carNumber.get()));
    }
}
