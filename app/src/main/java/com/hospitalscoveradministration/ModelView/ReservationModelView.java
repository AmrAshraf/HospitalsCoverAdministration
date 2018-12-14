package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hospitalscoveradministration.Model.Reservation;
import com.hospitalscoveradministration.Model.ReservationData;
import com.hospitalscoveradministration.Repository.ReservationRepo;
import com.hospitalscoveradministration.Repository.UpdateResourseActivityRepo;
import com.hospitalscoveradministration.webServicse.CansleRequest;
import com.hospitalscoveradministration.webServicse.RserveationResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReservationModelView extends ViewModel {

    private MutableLiveData<RserveationResponse> allReservation = new MutableLiveData<>();

    public MutableLiveData<RserveationResponse> getReservation() {
        return allReservation;
    }
    private MutableLiveData<Boolean> cancele = new MutableLiveData<>();

    public MutableLiveData<Boolean> getCancele() {
        return cancele;
    }

    public void canceleBooking(CansleRequest reservation) {
        ReservationRepo.getInstance().canceleBooking(reservation).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        cancele.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancele.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getAllReservation(String id){
        ReservationRepo.getInstance().getAllReservation(id).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<RserveationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RserveationResponse value) {
                allReservation.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                allReservation.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
