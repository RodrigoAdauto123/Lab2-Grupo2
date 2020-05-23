package com.example.lab2_grupo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.lab2_grupo2.entitades.DtoEmpleado;
import com.example.lab2_grupo2.entitades.Empleado;
import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity {

    static String apiKey;

    public void obtenerDeInternet(View view){

        String url="http://ec2-54-165-73-192.compute-1.amazonaws.com:9000";
        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DtoEmpleado dtoEmpleado = gson.fromJson(response, DtoEmpleado.class);
                        Empleado[] listaEmpleados = dtoEmpleado.getLista();
                        ListaEmpleadosAdapter listaEmpleadosAdapter = new ListaEmpleadosAdapter(listaEmpleados, Main2Activity.this);
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setAdapter(listaEmpleadosAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                    }}

    }



                        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
}
