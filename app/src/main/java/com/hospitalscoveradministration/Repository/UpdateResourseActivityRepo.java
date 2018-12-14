package com.hospitalscoveradministration.Repository;

import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.webServicse.CategoryWebService;
import com.hospitalscoveradministration.webServicse.UpdateRequest;
import com.hospitalscoveradministration.webServicse.UpdateResourseActivityWebServise;
import com.hospitalscoveradministration.webServicse.UpdateResponse;

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

public class UpdateResourseActivityRepo {
    private UpdateResourseActivityWebServise updateWebService;

    // region singleton implementation
    private static class Loader {
        static UpdateResourseActivityRepo INSTANCE = new UpdateResourseActivityRepo();
    }

    private UpdateResourseActivityRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        updateWebService = retrofit.create(UpdateResourseActivityWebServise.class);
    }

    public static UpdateResourseActivityRepo getInstance() {
        return UpdateResourseActivityRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "

    public Observable<Boolean> update(UpdateRequest request){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                updateWebService.updateResourse(request).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<UpdateResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateResponse value) {
                           if(value!=null && value.message.equals("successful update")){
                               emitter.onNext(true);
                           }else {
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
