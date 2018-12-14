package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Model.Result;
import com.hospitalscoveradministration.Model.SubCategory;
import com.hospitalscoveradministration.Repository.ResultRepo;
import com.hospitalscoveradministration.Repository.SubCategoryRepo;
import com.hospitalscoveradministration.webServicse.FilterRequest;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubCategoryModelView extends ViewModel {
    private MutableLiveData<SubCategory> allSubCategory = new MutableLiveData<>();
    private MutableLiveData<Result> result = new MutableLiveData<>();

    public MutableLiveData<SubCategory> getSubCategory() {
        return allSubCategory;
    }

    public MutableLiveData<Result> getResult() {
        return result;
    }

    public void getAllSubCategory(String id) {
        SubCategoryRepo.getInstance().getAllSubCategory(id).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<SubCategory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SubCategory value) {
                allSubCategory.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allSubCategory.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void getFilterHospitals(FilterRequest filterRequest) {
        SubCategoryRepo.getInstance().getResults(filterRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result value) {

                result.postValue(value);
            }

            @Override
            public void onError(Throwable e) {

                result.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });


    }
}