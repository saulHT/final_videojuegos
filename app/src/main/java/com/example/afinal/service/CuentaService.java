package com.example.afinal.service;

import com.example.afinal.entidades.Cuenta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CuentaService {

    @GET("cuenta")
    Call<List<Cuenta>>listaCuenta();

    @POST("cuenta")
    Call<Cuenta>crearCeunta(@Body Cuenta cuenta);
}
