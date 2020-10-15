package com.sanyue.jetpakcdemonew.liveDataBinding;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sanyue.jetpakcdemonew.R;
import com.sanyue.jetpakcdemonew.base.BaseActivity;
import com.sanyue.jetpakcdemonew.bean.User;
import com.sanyue.jetpakcdemonew.databinding.ActivityTwoWayBinding;
import com.sanyue.jetpakcdemonew.liveDataBinding.utils.BindingAdapters;

import java.util.Random;

/**
 * https://developer.android.google.cn/topic/libraries/data-binding/two-way
 * DataBinding和liveData 进行双向数据绑定
 * 在前面改变TextView的赋值是件套liveData数据更新然后手动进行改文本
 * 下面的例子将摆脱这种赋值直接把通过liveData自动去改变TextView的文本以及图片等视图改变
 * 这种改变都是通过静态方法去实现@BindingAdapter注解 google已经帮我们实现了几种 具体参考上方链接
 * 除了这种注解方式google也提供了去布局变量继承BaseObservable去实现 不例外的也提供ObservableIntInt
 * ObservableDouble ObservableChar  ObservableField ObservableByte
 * ObservableBoolean ObservableFloat ObservableShort ObservableLong这几个
 *
 */
public class TwoWayActivity extends BaseActivity implements View.OnClickListener {
    private TwoWayViewModel twoWayViewModel;
    private ActivityTwoWayBinding binding;
    private ListAdapter adapter;
    private  RecycleAdapter recycleAdapter;
    private String[] books={"c++入门到放弃","MySql入门到删库跑路","java入门到放弃","Android入门到放弃","Python入门到放弃","VB入门到放弃",
            "PS入门到放弃","PR入门到放弃","Swift入门到放弃","Object-C入门到放弃"};
    @Override
    public void init() {
        twoWayViewModel = new ViewModelProvider(this).get(TwoWayViewModel.class);
        binding = getViewDataBinding();
        binding.setOnclick(this);
        binding.setVieModel(twoWayViewModel);
        binding.setLifecycleOwner(this);
    //这里要注意的是DataBinding 是提供 AdapterView的适配器 AdapterViewBindingAdapter
        // 所以可以使用android:selectedItemPosition和android:selection android:adapter进行绑定
        // 也就是说继承了AdapterView的类可以这么用 recycleView不是继承AdapterView所以手动狗头
        adapter = new ListAdapter(twoWayViewModel.observableArrayList);
        binding.setAdapter(adapter);
        recycleAdapter = new RecycleAdapter(twoWayViewModel.books);
        binding.setLayout(new LinearLayoutManager(this));
        binding.setRecycleAdapter(recycleAdapter);
        binding.setL1(listener);
        binding.setS1(s1);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_two_way;
    }
    private Book book;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                twoWayViewModel.addUser(new User("张三",18));
                break;
            case R.id.createCarNumber:
                twoWayViewModel.createNumber();
                break;
            case R.id.addVBook:
                if(twoWayViewModel.books.size()>=books.length){
                    Toast.makeText(TwoWayActivity.this,"没书看了",1).show();
                    return;
                }
                Random random = new Random();
                book  = new Book(books[twoWayViewModel.books.size()],random.nextInt(888)+111);
                binding.setBook(book);
                twoWayViewModel.addBook(book);
                break;
            case R.id.score:
                if(book==null){
                    Toast.makeText(TwoWayActivity.this,"先选本书看吧",1).show();
                    return;
                }else {
                    book.setName("mysql入门到删库跑路");
                    book.setPages(233);
                }
                break;
        }
    }

    private BindingAdapters.OnScrollStateChanged s1= new BindingAdapters.OnScrollStateChanged() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            Log.e("TAGSSS","onScrollStateChanged");

        }
    };

    private ViewBindingAdapter.OnViewDetachedFromWindow listener=  new ViewBindingAdapter.OnViewDetachedFromWindow() {
        @Override
        public void onViewDetachedFromWindow(View v) {
            Log.e("TAGSSS","onViewDetachedFromWindow");
        }
    };
}