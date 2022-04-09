package com.example.hotelreservationsystem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiInterface {

    // API's endpoints
    @GET("/read/")
    public void getHotelsLists(Callback<List<HotelListData>> callback);
}
