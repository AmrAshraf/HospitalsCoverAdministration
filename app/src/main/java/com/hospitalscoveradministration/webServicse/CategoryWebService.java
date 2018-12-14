package com.hospitalscoveradministration.webServicse;




import com.hospitalscoveradministration.Model.Category;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoryWebService {

    @GET("Categories-getCategories")
    Observable<Category> getAllCategory(@Header("Authorization") String mac);

}
