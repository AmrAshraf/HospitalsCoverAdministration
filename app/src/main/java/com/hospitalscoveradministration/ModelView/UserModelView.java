package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Model.User;
import com.hospitalscoveradministration.Repository.UserRepo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserModelView extends ViewModel {

    private MutableLiveData<User> allUser = new MutableLiveData<>();

    public MutableLiveData<User> getUser() {
        return allUser;
    }


    public void getAllUser(String userID) {
        UserRepo.getInstance().getAllUser(userID).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User value) {
                allUser.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allUser.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
