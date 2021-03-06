package com.hospitalscoveradministration.Repository;



import com.hospitalscoveradministration.Model.SubCategory;
import com.hospitalscoveradministration.webServicse.ResourseResponse;
import com.hospitalscoveradministration.webServicse.SubCategoryWebService;

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

public class SubCategoryRepo {
    private SubCategoryWebService subCategoryWebService;

    // region singleton implementation
    private static class Loader {
        static SubCategoryRepo INSTANCE = new SubCategoryRepo();
    }

    private SubCategoryRepo() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://us-central1-vodafone-hospitals-cover.cloudfunctions.net/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        subCategoryWebService = retrofit.create(SubCategoryWebService.class);
    }

    public static SubCategoryRepo getInstance() {
        return SubCategoryRepo.Loader.INSTANCE;
    }
    // endregion
    // all function that connect to api "end Point "


    public Observable<SubCategory> getAllSubCategory(String id) {
        return Observable.create(new ObservableOnSubscribe<SubCategory>() {
            @Override
            public void subscribe(ObservableEmitter<SubCategory> emitter) throws Exception {
                subCategoryWebService.getAllSubCategory(id).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Observer<SubCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubCategory value) {

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
    public Observable<ResourseResponse> getResourse(String hospitalId,String subCategoryId){

        return Observable.create(new ObservableOnSubscribe<ResourseResponse>() {
            @Override
            public void subscribe(ObservableEmitter<ResourseResponse> emitter) throws Exception {
                subCategoryWebService.getResourse(hospitalId,subCategoryId).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                        .subscribe(new Observer<ResourseResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResourseResponse value) {
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
