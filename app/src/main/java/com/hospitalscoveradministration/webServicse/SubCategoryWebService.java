package com.hospitalscoveradministration.webServicse;

import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.Model.Result;
import com.hospitalscoveradministration.Model.SubCategory;

import java.util.logging.Filter;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SubCategoryWebService {
    @GET("Categories-getSubCategories/?")
    Observable<SubCategory> getAllSubCategory(@Query("categoryId") String id);

    @POST("Hospital-filterByDestination")
    Observable<Result> getNeartsHospitals(@Body FilterRequest request);


}
