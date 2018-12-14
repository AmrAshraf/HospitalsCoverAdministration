package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hospitalscoveradministration.Model.Category;
import com.hospitalscoveradministration.Model.CategoryData;
import com.hospitalscoveradministration.Model.Reservation;
import com.hospitalscoveradministration.Model.ReservationData;
import com.hospitalscoveradministration.Model.SubCategory;
import com.hospitalscoveradministration.Model.SubCategoryData;

public class ResponseDate {
    @SerializedName("category")
    @Expose
    public CategoryData category;
    @SerializedName("subCategory")
    @Expose
    public SubCategoryData subCategory;
    @SerializedName("reservation")
    @Expose
    public ReservationData reservation;


}
