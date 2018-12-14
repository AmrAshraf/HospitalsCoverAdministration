package com.hospitalscoveradministration.Repository;

import com.hospitalscoveradministration.webServicse.ReservationWebService;

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



}
