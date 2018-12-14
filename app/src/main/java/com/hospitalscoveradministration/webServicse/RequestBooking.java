package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBooking {
    @SerializedName("subCategoryId")
    @Expose
    public String subCategoryId;
    @SerializedName("hospitalId")
    @Expose
    public String hospitalId;
    @SerializedName("macAddress")
    @Expose
    public String macAddress;
    @SerializedName("phone")
    @Expose
    public String phone;
}
