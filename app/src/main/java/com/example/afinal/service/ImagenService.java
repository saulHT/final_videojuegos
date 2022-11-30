package com.example.afinal.service;

import com.example.afinal.entidades.Image;
import com.example.afinal.entidades.ImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImagenService {

    @POST("3/image")
    Call<ImageResponse> senImage(@Body Image image);
}
