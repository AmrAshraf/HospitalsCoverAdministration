package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateRequest {
    @SerializedName("hospitalResourceId")
    @Expose
    public String hospitalResourceId;
    @SerializedName("available")
    @Expose
    public Integer available;
    @SerializedName("availabilityTime")
    @Expose
    public Integer availabilityTime;
}
