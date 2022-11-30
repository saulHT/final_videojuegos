package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.afinal.adapter.MovimientoAdapter;
import com.example.afinal.entidades.Cuenta;
import com.example.afinal.entidades.Movimiento;
import com.example.afinal.factories.RetrofitFactory;
import com.example.afinal.service.MovimientoService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListMovimientoActivity extends AppCompatActivity {
private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movimiento);

        Intent intent=getIntent();
        String cuentasJson=intent.getStringExtra("data_lis");
        Cuenta cuenta=new Gson().fromJson(cuentasJson,Cuenta.class);
        id=cuenta.id;
        Retrofit retrofit=new RetrofitFactory(this).build("https://6352e45da9f3f34c374b10ef.mockapi.io/","");

        MovimientoService service=retrofit.create(MovimientoService.class);
        service.listamovimiento(id).enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                List<Movimiento>data=response.body();

                RecyclerView view=findViewById(R.id.rv_listmovimeintos);
                view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                view.setAdapter(new MovimientoAdapter(data));
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {

            }
        });
    }
}