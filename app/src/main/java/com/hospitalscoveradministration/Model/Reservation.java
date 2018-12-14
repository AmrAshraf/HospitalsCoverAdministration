package com.hospitalscoveradministration.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reservation {
    @SerializedName("data")
    @Expose
    private List<ReservationData> data = null;

    public List<ReservationData> getData() {
        return data;
    }

}
