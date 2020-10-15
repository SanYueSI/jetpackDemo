package com.sanyue.jetpakcdemonew.liveDataBinding.two;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableParcelable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.liveDataBinding.Book;

import java.util.Random;

/***
 * Create by Yip
 * Create Time 2020/10/10
 */
public class DemoViewModel extends ViewModel {
    public ObservableParcelable<Ab> ab= new ObservableParcelable<>();
    public ObservableBoolean flag= new ObservableBoolean(false);
    public ObservableArrayList<String> list= new ObservableArrayList();
    public ObservableInt count =new ObservableInt(0);
    private MediatorLiveData<User> userMediatorLiveData = new MediatorLiveData<>();
    public LiveData<User> userLiveData = userMediatorLiveData;
    public MediatorLiveData<String> stringMediatorLiveData =new MediatorLiveData<>();
    public ObservableField<User> userObservableField = new ObservableField<>();

    public MutableLiveData<String> message = new MutableLiveData<>();
    public MediatorLiveData<Integer> messageNumber = new MediatorLiveData<>();


    public void addUser(User user){
        userMediatorLiveData.setValue(user);
        stringMediatorLiveData.setValue(user+"你好");
        addList(stringMediatorLiveData.getValue());
        userObservableField.set(user);
    }
    public String getUser(){
        return userMediatorLiveData.getValue()==null?"毛都没有哦":userMediatorLiveData.getValue().getName()+"今年"+userMediatorLiveData.getValue().getAge();
    }

    public void add(){
        count.set(count.get()+1);
    }
    public void addList(String str){
        list.add(str);
    }

    public DemoViewModel() {
        ab.set(new Ab("学生"));
        stringMediatorLiveData.setValue("1234");
        messageNumber.setValue(0);
        messageNumber.addSource(message, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                messageNumber.setValue(s.length());
            }
        });
    }
    public Book book= new Book();
    public void addBooK(String name,int page){
        book.setName(name);
        book.setPages(page);
    }



    public static class Ab  implements Parcelable {
        public String name;

        public Ab(String name) {
            this.name = name;
        }

        protected Ab(Parcel in) {
            name = in.readString();
        }

        public static final Creator<Ab> CREATOR = new Creator<Ab>() {
            @Override
            public Ab createFromParcel(Parcel in) {
                return new Ab(in);
            }

            @Override
            public Ab[] newArray(int size) {
                return new Ab[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        messageNumber.removeSource(message);
    }
}
