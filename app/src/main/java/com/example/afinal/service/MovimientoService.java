package com.example.afinal.service;

import com.example.afinal.entidades.Cuenta;
import com.example.afinal.entidades.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MovimientoService {

    @GET("cuenta/{id}/movimiento")
    Call<List<Movimiento>> listamovimiento(@Path("id")int id);

    @POST("cuenta/{id}/movimiento")
    Call<Movimiento>crearmovimiento(@Body Movimiento movimiento,@Path("id")int id);
}
