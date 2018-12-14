package com.hospitalscoveradministration.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationData {
    @SerializedName("categoryId")
    @Expose
    public String categoryId;
    @SerializedName("hospitalResourceId")
    @Expose
    public String hospitalResourceId;
    @SerializedName("availabilityTime")
    @Expose
    public Integer availabilityTime;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("macAddress")
    @Expose
    public String macAddress;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("hospitalId")
    @Expose
    public String hospitalId;
    @SerializedName("subCategoryId")
    @Expose
    public String subCategoryId;


}
