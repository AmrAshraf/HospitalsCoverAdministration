package com.hospitalscoveradministration.webServicse;

import com.hospitalscoveradministration.Model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserWebService {

    @GET("User-getHospitalDataToAdmin?")
    Observable<User> getUser(@Query("userId") String id);
}
