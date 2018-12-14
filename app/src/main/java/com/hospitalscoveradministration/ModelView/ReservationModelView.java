package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Model.Reservation;
import com.hospitalscoveradministration.Model.Result;

import com.hospitalscoveradministration.Repository.SubCategoryRepo;
import com.hospitalscoveradministration.webServicse.FilterRequest;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReservationModelView extends ViewModel {
    private MutableLiveData<Reservation> allReservations = new MutableLiveData<>();
    private MutableLiveData<Result> result = new MutableLiveData<>();

    public MutableLiveData<Reservation> getReservations() {
        return allReservations;
    }

    public MutableLiveData<Result> getResult() {
        return result;
    }

    public void getAllSubCategory(String id) {
        ReservationRepo.getInstance().getAllSubCategory(id).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Reservation>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Reservation value) {
                allReservations.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allReservations.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void getFilterHospitals(FilterRequest filterRequest) {
        ReservationRepo.getInstance().getResults(filterRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<Result>() {
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
