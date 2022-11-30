package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.afinal.entidades.Cuenta;
import com.example.afinal.entidades.Movimiento;
import com.example.afinal.factories.RetrofitFactory;
import com.google.gson.Gson;

import retrofit2.Retrofit;

public class DetalleCuentaActivity extends AppCompatActivity {
    private Button but_agregar,but_listar,but_actualizar;
    private TextView cuentaTex;
    private Retrofit retrofit;
    private Cuenta cuenta=new Cuenta();
    private Movimiento movimiento=new Movimiento();
    private int idCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cuenta);

        cuentaTex=findViewById(R.id.textView_cuentaNombDetall);
        but_agregar=findViewById(R.id.but_registraDetallMovi);
        but_listar=findViewById(R.id.but_vermovimDetall);

        retrofit =new RetrofitFactory(this).build("https://6352e45da9f3f34c374b10ef.mockapi.io/","");

        Intent intent=getIntent();
        String cuentaJson=intent.getStringExtra("data-cuenta");

        cuenta=new Gson().fromJson(cuentaJson,Cuenta.class);
        cuentaTex.setText(cuenta.nombre);
        idCuenta=cuenta.id;

        but_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaMovimiento(cuenta);
            }
        });

        but_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarMovimiento(cuenta);
            }
        });
    }

    private void listaMovimiento(Cuenta cuenta) {

             Intent intent=new Intent(DetalleCuentaActivity.this,ListMovimientoActivity.class);
             intent.putExtra("data_lis",new Gson().toJson(cuenta));
             startActivity(intent);
    }

    private void agregarMovimiento(Cuenta cuenta) {

        Intent intent=new Intent(DetalleCuentaActivity.this,FromMovimientoActivity.class);
        intent.putExtra("data_agregar",new Gson().toJson(cuenta));
        startActivity(intent);
    }
}