package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.afinal.adapter.CuentaAdapter;
import com.example.afinal.entidades.Cuenta;
import com.example.afinal.factories.RetrofitFactory;
import com.example.afinal.service.CuentaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cuenta);


        Retrofit retrofit=new RetrofitFactory(this).build("https://6352e45da9f3f34c374b10ef.mockapi.io/","");
        CuentaService service=retrofit.create(CuentaService.class);
        service.listaCuenta().enqueue(new Callback<List<Cuenta>>() {
            @Override
            public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                List<Cuenta>data=response.body();

                RecyclerView view=findViewById(R.id.rv_listCuenta);
                view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                view.setAdapter(new CuentaAdapter(data));
            }

            @Override
            public void onFailure(Call<List<Cuenta>> call, Throwable t) {

            }
        });
    }
}