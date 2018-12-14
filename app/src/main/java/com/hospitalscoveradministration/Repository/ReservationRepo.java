package com.hospitalscoveradministration.Repository;

import com.hospitalscoveradministration.Model.ReservationData;
import com.hospitalscoveradministration.webServicse.CancelResponse;
import com.hospitalscoveradministration.webServicse.CansleRequest;
import com.hospitalscoveradministration.webServicse.ReservationWebService;
import com.hospitalscoveradministration.webServicse.RserveationResponse;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationRepo {
    private ReservationWebService reservationWebService;

    // region singleton implementation
    private static class Loader {
        static ReservationRepo INSTANCE = new ReservationRepo();
    }

    private ReservationRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        reservationWebService = retrofit.create(ReservationWebService.class);
    }

    public static ReservationRepo getInstance() {
        return ReservationRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "

    public Observable<RserveationResponse> getAllReservation(String id) {
        return Observable.create(new ObservableOnSubscribe<RserveationResponse>() {
            @Override
            public void subscribe(ObservableEmitter<RserveationResponse> emitter) throws Exception {
                reservationWebService.getAllReservation(id).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<RserveationResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RserveationResponse value) {
                        emitter.onNext(value);

                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onNext(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });

    }


    public Observable<Boolean> canceleBooking(CansleRequest reservation) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {

                reservationWebService.canclBooking(reservation).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                        .subscribe(new Observer<CancelResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CancelResponse value) {
                                if (value != null && value.message.equals("success")) {
                                    emitter.onNext(true);
                                } else {
                                    emitter.onNext(false);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                emitter.onNext(false);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


    }


}
