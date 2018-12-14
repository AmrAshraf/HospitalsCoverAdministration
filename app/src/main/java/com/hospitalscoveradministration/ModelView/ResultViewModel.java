package com.hospitalscoveradministration.ModelView;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.hospitalscoveradministration.Repository.ResultRepo;
import com.hospitalscoveradministration.webServicse.BookingResponse;
import com.hospitalscoveradministration.webServicse.RequestBooking;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResultViewModel extends ViewModel {
    private MutableLiveData<BookingResponse> resultbooking = new MutableLiveData<>();

    public MutableLiveData<BookingResponse> getResult() {
        return resultbooking;
    }

    public void booking(RequestBooking requestBooking) {
        ResultRepo.getInstance().booking(requestBooking).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<BookingResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BookingResponse value) {
                resultbooking.postValue(value);
            }

            @Override
            public void onError(Throwable e) {
                resultbooking.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
