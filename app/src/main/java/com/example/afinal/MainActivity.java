package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but_list=findViewById(R.id.but_cuentaLis);
         Button but_crear=findViewById(R.id.but_creaCuenta);

         but_list.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent=new Intent(MainActivity.this,ListCuentaActivity.class);
                 startActivity(intent);
             }
         });

         but_crear.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,FromCuentaActivity.class);
                 startActivity(intent);
             }
         });
    }
}