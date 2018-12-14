package com.hospitalscoveradministration.webServicse;



import com.hospitalscoveradministration.Model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CategoryWebService {

    @GET("Categories-getCategories")
    Observable<Category> getAllCategory();
 }
