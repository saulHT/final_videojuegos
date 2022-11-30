package com.example.afinal.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.DetalleCuentaActivity;
import com.example.afinal.R;
import com.example.afinal.entidades.Cuenta;
import com.google.gson.Gson;

import java.util.List;

public class CuentaAdapter extends RecyclerView.Adapter {

    List<Cuenta>data;
    public CuentaAdapter(List<Cuenta>data){
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_listacuenta,parent,false);
        return new CuentaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TextView textView=holder.itemView.findViewById(R.id.textVi_nombCuentalis);

        Cuenta cuenta=data.get(position);
        textView.setText(cuenta.nombre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(holder.itemView.getContext(), DetalleCuentaActivity.class);
                intent.putExtra("data-cuenta",new Gson().toJson(cuenta));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CuentaHolder extends RecyclerView.ViewHolder {
        public CuentaHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
