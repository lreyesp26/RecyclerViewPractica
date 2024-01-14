package com.example.recyclerview.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.Modelos.LugarModelo;
import com.example.recyclerview.R;

import java.util.List;

public class LugarAdaptador extends RecyclerView.Adapter<LugarAdaptador.LugarViewHolder> {

    private Context context;
    private List<LugarModelo> lugarList;

    public LugarAdaptador(Context context, List<LugarModelo> lugarList) {
        this.lugarList = lugarList;
        this.context = context;
    }

    @Override
    public LugarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ly_lugares, parent, false);
        return new LugarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LugarViewHolder holder, int position) {
        LugarModelo lugar = lugarList.get(position);
        holder.bindData(lugar);
    }

    @Override
    public int getItemCount() {
        return lugarList.size();
    }

    class LugarViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDireccion, textViewTelefono;
        ImageView imageView;

        public LugarViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewDireccion = itemView.findViewById(R.id.txtDireccion);
            textViewTelefono = itemView.findViewById(R.id.txtTelefono);
            imageView = itemView.findViewById(R.id.imgLogo);
        }

        public void bindData(LugarModelo lugar) {
            textViewName.setText(lugar.getNombre());
            textViewDireccion.setText(lugar.getDireccion());
            textViewTelefono.setText(lugar.getTelefono());

            Glide.with(context)
                    .load(lugar.getUrlLOgo())
                    .into(imageView);
        }
    }
}
