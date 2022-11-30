package com.example.afinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.afinal.entidades.Cuenta;
import com.example.afinal.entidades.Image;
import com.example.afinal.entidades.ImageResponse;
import com.example.afinal.entidades.Movimiento;
import com.example.afinal.factories.RetrofitFactory;
import com.example.afinal.service.ImagenService;
import com.example.afinal.service.MovimientoService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FromMovimientoActivity extends AppCompatActivity {

    private final static int CAMERA_REQUEST=1000;
 private Movimiento movimiento=new Movimiento();
   private Cuenta cuenta =new Cuenta();
   private EditText tipo,monto,motivo;
   private Button tomarFoto,guardaMov;
   private ImageView img;
   private Retrofit retrofit;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_movimiento);

        tipo=findViewById(R.id.editText_movTipo);
        monto=findViewById(R.id.editText_movMonto);
        motivo=findViewById(R.id.editText_MovMotiv);
        img=findViewById(R.id.imageView_tomoFotoMovim);
        tomarFoto=findViewById(R.id.but_tomaFotoVauc);
        guardaMov=findViewById(R.id.but_guardMoviminet);
        retrofit =new RetrofitFactory(this).build("https://6352e45da9f3f34c374b10ef.mockapi.io/","");

        Intent intent=getIntent();
        String cuentaJson=intent.getStringExtra("data_agregar");

        cuenta=new Gson().fromJson(cuentaJson,Cuenta.class);

      tomarFoto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                  abrirCamara();
              }else {
                  requestPermissions(new String[]{Manifest.permission.CAMERA},100);
              }
          }
      });
      guardaMov.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              guardarMovim(cuenta,movimiento);
          }
      });

    }

    private void guardarMovim(Cuenta cuenta,Movimiento movimiento) {

        movimiento.imagen=url;
        movimiento.monto=monto.getText().toString();
        movimiento.motivo=motivo.getText().toString();
        movimiento.tipo=tipo.getText().toString();

        MovimientoService service=retrofit.create(MovimientoService.class);
        service.crearmovimiento(movimiento,cuenta.id).enqueue(new Callback<Movimiento>() {
            @Override
            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {

            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {

            }
        });
    }

    private void abrirCamara() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imgeBitmap= (Bitmap) extras.get("data");
            img.setImageBitmap(imgeBitmap);

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            imgeBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            byte[]bytesArray=byteArrayOutputStream.toByteArray();

            String imageBase64= Base64.encodeToString(bytesArray,Base64.DEFAULT);

            Retrofit retrofi=new RetrofitFactory(this).build("https://api.imgur.com/","Client-ID 8bcc638875f89d9");

            ImagenService imagenService=retrofi.create(ImagenService.class);
            Image image=new Image();
            image.image=imageBase64;

            imagenService.senImage(image).enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    ImageResponse r=response.body();

                    url=r.data.link;
                    Log.i("MAIN_APP","url:"+r.data.link);
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {

                }
            });
        }

        if(requestCode==1001){
            String[] filePathColumn={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(data.getData(),filePathColumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            String picturePath =cursor.getString(columnIndex);
            cursor.close();
            Bitmap imageBitmap= BitmapFactory.decodeFile(picturePath);
            img.setImageBitmap(imageBitmap);

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            byte[] bytesArrray=byteArrayOutputStream.toByteArray();

            String encoded=Base64.encodeToString(bytesArrray,Base64.DEFAULT);
            Log.i("MAIN_APP","base: "+encoded);

        }
    }
}