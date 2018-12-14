package com.hospitalscoveradministration.webServicse;



import com.hospitalscoveradministration.Model.SubCategory;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SubCategoryWebService {
    @GET("Categories-getSubCategories/?")
   // @Headers({"Authorization"})
    Observable<SubCategory> getAllSubCategory(@Query("categoryId") String id);



}
