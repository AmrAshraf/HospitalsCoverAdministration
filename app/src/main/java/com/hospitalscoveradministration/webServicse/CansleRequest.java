package com.hospitalscoveradministration.webServicse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hospitalscoveradministration.Model.ReservationData;

public class CansleRequest {
    @SerializedName("reservation")
    @Expose
    public ReservationData reservationData;
}
