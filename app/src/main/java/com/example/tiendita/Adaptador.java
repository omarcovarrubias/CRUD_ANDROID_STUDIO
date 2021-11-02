package com.example.tiendita;

import android.content.Context;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.viewholder> {
    private List<Productos>productos;
    private Context  context;
    public Adaptador(List<Productos>productos,Context context){
        this.productos=productos;
        this.context=context;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
    holder.descripcion.setText(productos.get(position).getDescripcion());
    holder.codigo.setText(productos.get(position).getCodigo());
    holder.idproducto.setText(productos.get(position).getIdproducto());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    public static  class viewholder extends RecyclerView.ViewHolder{
        TextView  codigo,descripcion,idproducto;
        public viewholder(@NonNull View itemView){
            super(itemView);
            descripcion=itemView.findViewById(R.id.descripcion);
            codigo=itemView.findViewById(R.id.codigo);
            idproducto=itemView.findViewById(R.id.idproducto);
        }
    }
}
