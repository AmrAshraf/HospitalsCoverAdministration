package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResourseResponseData  implements Serializable {
    @SerializedName("pending")
    @Expose
    public Integer pending;
    @SerializedName("availabilityTime")
    @Expose
    public Integer availabilityTime;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("available")
    @Expose
    public Integer available;
    @SerializedName("hospitalId")
    @Expose
    public String hospitalId;
    @SerializedName("subCategoryId")
    @Expose
    public String subCategoryId;
}
