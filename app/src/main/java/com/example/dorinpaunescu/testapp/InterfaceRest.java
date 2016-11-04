package com.example.dorinpaunescu.testapp;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;

public interface InterfaceRest {

    // This method is used for "POST"
    @GET("/posts/1")
    void getData(Callback<Response> serverResponseCallback);

}