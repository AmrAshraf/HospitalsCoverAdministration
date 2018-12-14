package com.hospitalscoveradministration.webServicse;



import com.hospitalscoveradministration.Model.Reservation;
import com.hospitalscoveradministration.Model.ReservationData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  ReservationWebService {
    @GET("Booking-getBookings/?")
    Observable<RserveationResponse> getAllReservation(@Query("hospitalId") String id);

    @POST("Booking-cancelRequiest")
    Observable<CancelResponse> canclBooking(@Body CansleRequest reservation);

}
