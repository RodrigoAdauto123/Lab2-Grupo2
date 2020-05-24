package com.example.lab2_grupo2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lab2_grupo2.entitades.Trabajo;
import com.example.lab2_grupo2.entitades.CustomOnClickListener;

public class ListaTrabajosAdapter extends RecyclerView.Adapter<ListaTrabajosAdapter.TrabajoViewHolder>  {
static Trabajo[] listaTrabajoListars;
static Context contexto;
static int mPosition;
public ListaTrabajosAdapter(Trabajo[] lista, Context c){
    this.listaTrabajoListars =lista;
    this.contexto=c;
}
public static class TrabajoViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView textView10;
    Button editarButton;
    Button borrarButton;
    public TrabajoViewHolder(View itemView){
        super(itemView);
        cardView=itemView.findViewById(R.id.cardView1);
        textView10=itemView.findViewById(R.id.textView10);
        borrarButton= itemView.findViewById(R.id.borrarButton);

        editarButton=itemView.findViewById(R.id.editarButton);
        editarButton.setOnClickListener(new CustomOnClickListener( mPosition,listaTrabajoListars){
                                            @Override
                                            public void onClick(View view) {





                                                if(listaTrabajoListars[mPosition].getCreateBy() != null){
                                                    Intent intent = new Intent(view.getContext(),EditarTrabajoActivity.class);
                                                    intent.putExtra("NombreTrabajo",listaTrabajoListars[mPosition].getJobTitle());
                                                    intent.putExtra("SalarioMaximo",listaTrabajoListars[mPosition].getMaxSalary());
                                                    intent.putExtra("SalarioMinimo",listaTrabajoListars[mPosition].getMinSalary());

                                                    intent.putExtra("JobId",listaTrabajoListars[mPosition].getJobId());
                                                    intent.putExtra("api-key","TPyPr3quVcAK8UYSGq6c");
                                                    contexto.startActivity(intent);

                                                }else {

                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                                                    alertDialog.setTitle("Acceso Degenado");
                                                    alertDialog.setMessage("No se puede editar este trabajo porque no fue creado por usted");
                                                    alertDialog.setPositiveButton("De Acuerto", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    });
                                                    alertDialog.show();
                                                }


                                            }


                                        }


        );

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
    Trabajo t = listaTrabajoListars[position];
    String text=t.getJobTitle();
    holder.textView10.setText(text);
     mPosition=position;



    }

    @Override
    public int getItemCount() {
        return listaTrabajoListars.length;
    }
}
