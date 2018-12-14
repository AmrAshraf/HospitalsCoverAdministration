package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Repository.UpdateResourseActivityRepo;
import com.hospitalscoveradministration.webServicse.UpdateRequest;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpdateResourseActivityModelView extends ViewModel {

    private MutableLiveData<Boolean> update=new MutableLiveData<>();

    public MutableLiveData<Boolean> getUpdate() {
        return update;
    }
    public void updateResouse(UpdateRequest request ){
        UpdateResourseActivityRepo.getInstance().update(request).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                              update.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                            update.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
