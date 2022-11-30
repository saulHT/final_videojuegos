package com.example.afinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.entidades.Movimiento;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovimientoAdapter extends RecyclerView.Adapter {

    List<Movimiento>data;
    public MovimientoAdapter(List<Movimiento>data){
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_listmovimiento,parent,false);

        return new MoviminetoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TextView text_tipo=holder.itemView.findViewById(R.id.textView_tipoMov);
        TextView text_monto=holder.itemView.findViewById(R.id.textView_montoMovi);
        TextView text_motivo=holder.itemView.findViewById(R.id.textView_motivoMovim);
        ImageView imageV=holder.itemView.findViewById(R.id.imageView_imgMovimiento);

        Movimiento movimiento=data.get(position);
        text_tipo.setText(movimiento.tipo);
        text_monto.setText(movimiento.monto);
        text_motivo.setText(movimiento.motivo);

        Picasso.get()
                .load(movimiento.imagen)
                .into(imageV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MoviminetoHolder extends RecyclerView.ViewHolder {
        public MoviminetoHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
