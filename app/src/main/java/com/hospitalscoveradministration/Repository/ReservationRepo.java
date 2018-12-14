package com.hospitalscoveradministration.Repository;

import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.Model.Reservation;
import com.hospitalscoveradministration.webServicse.CategoryWebService;

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

public class ReservationRepo{
        private ReservationWebService reservation;

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
        reservation = retrofit.create(ReservationWebService.class);
    }

    public static ReservationRepo getInstance() {
        return ReservationRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "


    public Observable<Category> getAllReservation() {
        return Observable.create(new ObservableOnSubscribe<Category>() {
            @Override
            public void subscribe(ObservableEmitter<Category> emitter) throws Exception {
                reservation.getAllReservation().subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Observer<Reservation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Reservation value) {
                        emitter.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        emitter.onNext(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }
        });

    }
}
