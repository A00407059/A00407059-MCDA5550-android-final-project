package com.example.hotelreservationsystem;
import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://10.0.2.2:8000") //Root URL for django backend
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object


    }
}
