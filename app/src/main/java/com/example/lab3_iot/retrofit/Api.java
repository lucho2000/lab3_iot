package com.example.lab3_iot.retrofit;

import com.example.lab3_iot.entity.Respuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    //metodos
    @GET("/api/")
    Call<Respuesta> getResult();
}
