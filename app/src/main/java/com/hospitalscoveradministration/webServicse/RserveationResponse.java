package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class RserveationResponse  {
    @SerializedName("data")
    @Expose
    public List<ResponseDate> data = null;


}
