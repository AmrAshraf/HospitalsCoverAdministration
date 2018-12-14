package com.hospitalscoveradministration.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData implements Serializable {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("destination")
    @Expose
    public Destination destination;
    @SerializedName("workingHours")
    @Expose
    public String workingHours;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("name")
    @Expose
    public String name;

}
