package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.hospitalscoveradministration.Model.SubCategory;
import com.hospitalscoveradministration.Repository.SubCategoryRepo;
import com.hospitalscoveradministration.webServicse.ResourseResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubCategoryModelView extends ViewModel {
    private MutableLiveData<SubCategory> allSubCategory = new MutableLiveData<>();
    private MutableLiveData<ResourseResponse> resourse = new MutableLiveData<>();

    public MutableLiveData<SubCategory> getSubCategory() {
        return allSubCategory;
    }

    public MutableLiveData<ResourseResponse> getResourse() {
        return resourse;
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

    public void getResource(String hospitalId, String subCategoryId) {
        SubCategoryRepo.getInstance().getResourse(hospitalId, subCategoryId).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<ResourseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResourseResponse value) {
                        resourse.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        resourse.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
