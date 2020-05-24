package com.example.lab2_grupo2.entitades;

import android.view.View;

public class CustomOnClickListenerEmpleados implements View.OnClickListener
{

    int position;
    Empleado[] listaEmpleados;
    public CustomOnClickListenerEmpleados(int position, Empleado[] lista ) {
        this.position = position;
        this.listaEmpleados =lista;
    }

    @Override
    public void onClick(View v)
    {
        //read your lovely variable
    }

};