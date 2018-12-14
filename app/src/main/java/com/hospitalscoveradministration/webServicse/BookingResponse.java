package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingResponse {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("available")
    @Expose
    public Boolean available;


}
