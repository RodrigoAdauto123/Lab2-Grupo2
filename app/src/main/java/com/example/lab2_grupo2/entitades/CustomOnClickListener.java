package com.example.lab2_grupo2.entitades;

import android.view.View;

public class CustomOnClickListener implements View.OnClickListener
{

    int position;
    Trabajo[] listaTrabajoListars;
    public CustomOnClickListener(int position, Trabajo[] lista ) {
        this.position = position;
        this.listaTrabajoListars =lista;
    }

    @Override
    public void onClick(View v)
    {
        //read your lovely variable
    }

};