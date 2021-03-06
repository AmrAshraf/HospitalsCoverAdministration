package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.Repository.CategoryRepo;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryModelView extends ViewModel {
    private MutableLiveData<Category> allCategory = new MutableLiveData<>();

    public MutableLiveData<Category> getCategory() {
        return allCategory;
    }


    public void getAllCategory(String mac) {
        CategoryRepo.getInstance().getAllCategory(mac).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new io.reactivex.Observer<Category>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Category value) {
                allCategory.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allCategory.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
