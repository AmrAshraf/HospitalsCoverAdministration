package com.hospitalscoveradministration.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationData {
    @SerializedName("reservationID")
    @Expose
    private String id;
    @SerializedName("categoryIcon")
    @Expose
    private String icon;
    @SerializedName("categoryName")
    @Expose
    private String name;

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @SerializedName("subCategoryName")
    @Expose
    private String subname;
    public String getId() {
        return id;
    }

    @SerializedName("reservationPhone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
