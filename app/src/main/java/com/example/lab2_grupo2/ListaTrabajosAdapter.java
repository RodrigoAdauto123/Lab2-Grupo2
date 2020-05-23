package com.example.lab2_grupo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lab2_grupo2.entitades.TrabajoListar;

public class ListaTrabajosAdapter extends RecyclerView.Adapter<ListaTrabajosAdapter.TrabajoViewHolder> {
TrabajoListar[] listaTrabajoListars;
Context contexto;
public ListaTrabajosAdapter(TrabajoListar[] lista, Context c){
    this.listaTrabajoListars =lista;
    this.contexto=c;
}
public static class TrabajoViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView textView10;
    public TrabajoViewHolder(View itemView){
        super(itemView);
        cardView=itemView.findViewById(R.id.cardView1);
        textView10=itemView.findViewById(R.id.textView10);

    }



}
    @NonNull
    @Override
    public TrabajoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemview= LayoutInflater.from(contexto).inflate(R.layout.item_rv,parent,false);
    TrabajoViewHolder trabajoViewHolder =new TrabajoViewHolder(itemview);


        return trabajoViewHolder;
    }

    @Override
    public void onBindViewHolder(TrabajoViewHolder holder, int position) {
    TrabajoListar t = listaTrabajoListars[position];
    String text=t.getJobTitle();
    holder.textView10.setText(text);


    }

    @Override
    public int getItemCount() {
        return listaTrabajoListars.length;
    }
}
