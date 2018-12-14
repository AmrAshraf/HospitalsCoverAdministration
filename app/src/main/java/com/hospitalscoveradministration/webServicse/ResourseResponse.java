package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResourseResponse implements Serializable {
    @SerializedName("data")
    @Expose
    public List<ResourseResponseData> data = null;
}
