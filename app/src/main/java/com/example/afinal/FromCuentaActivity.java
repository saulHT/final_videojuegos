package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afinal.entidades.Cuenta;
import com.example.afinal.factories.RetrofitFactory;
import com.example.afinal.service.CuentaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FromCuentaActivity extends AppCompatActivity {

    private EditText edit_nombre;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_cuenta);

          edit_nombre=findViewById(R.id.editText_nombreCeuntaCrea);
        retrofit =new RetrofitFactory(this).build("https://6352e45da9f3f34c374b10ef.mockapi.io/","");

        Button butGuardar=findViewById(R.id.but_creaCuentas);

        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cuenta cuenta=new Cuenta();
                cuenta.nombre=edit_nombre.getText().toString();

                CuentaService service=retrofit.create(CuentaService.class);
                service.crearCeunta(cuenta).enqueue(new Callback<Cuenta>() {
                    @Override
                    public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {
                        Toast.makeText(FromCuentaActivity.this,"guardado",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Cuenta> call, Throwable t) {

                    }
                });
            }
        });
    }
}