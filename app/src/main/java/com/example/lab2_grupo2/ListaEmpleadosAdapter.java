package com.example.lab2_grupo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clase5.entitades.Empleado;
import com.example.lab2_grupo2.entitades.Empleado;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {
Empleado[] listaEmpleados;
Context contexto;
public ListaEmpleadosAdapter(Empleado[] lista, Context c){
    this.listaEmpleados=lista;
    this.contexto=c;


}
public static class EmpleadoViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView textView10;
    public EmpleadoViewHolder(  View itemView){
        super(itemView);
        cardView=itemView.findViewById(R.id.cardView1);
        textView10=itemView.findViewById(R.id.textView10);

    }



}
    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemview= LayoutInflater.from(contexto).inflate(R.layout.item_rv,parent,false);
    EmpleadoViewHolder empleadoViewHolder=new EmpleadoViewHolder(itemview);


        return empleadoViewHolder;
    }

    @Override
    public void onBindViewHolder( EmpleadoViewHolder holder, int position) {
    Empleado e =listaEmpleados[position];
    String text=e.getFirst_name()+"" + e.getLast_name();
    holder.textView10.setText(text);


    }

    @Override
    public int getItemCount() {
        return listaEmpleados.length;
    }
}
