package com.hospitalscoveradministration.webServicse;



import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UpdateResourseActivityWebServise {

    @POST("User-updateHospitalResources")
    Observable<UpdateResponse> updateResourse(@Body UpdateRequest request);
}
