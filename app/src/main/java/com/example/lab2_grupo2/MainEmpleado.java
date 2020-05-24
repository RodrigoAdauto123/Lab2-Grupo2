package com.example.lab2_grupo2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2_grupo2.EditarEmpleadoActivity;
import com.example.lab2_grupo2.entitades.Empleado;
import com.example.lab2_grupo2.entitades.DtoEmpleado;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class MainEmpleado extends AppCompatActivity {

    String api_key;
    Empleado[] listaEmpleados;
    String idEmployee;
    int i;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appbar, menu);

        return true;
    }


    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
            return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null)
                return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null)
                return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null)
                return false;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET)
                return true;
            return false;

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String url="http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";
        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Gson gson=new Gson();
                        DtoEmpleado dtoEmpleado= gson.fromJson(response, DtoEmpleado.class);

                        listaEmpleados = dtoEmpleado.getEmpleados();

                        ListaEmpleadosAdapter listaEmpleadosAdapter =new ListaEmpleadosAdapter(listaEmpleados, MainEmpleado.this);

                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setAdapter(listaEmpleadosAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainEmpleado.this));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("mensa","ERRoVOL");

            }}){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras =new HashMap<>();
                cabeceras.put("api-key",api_key);
                return cabeceras;


            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void vistaEditarEmpleado(View view, int employeeId){

        int i= 0;
        for (int z = 0; z<listaEmpleados.length;z++){
            if(listaEmpleados[z].getEmployee_id() == idEmployee ){
                i = z;
            }
        }

        if(listaEmpleados[i].getCreateBy() != null){
            Intent intent = new Intent(this, EditarEmpleadoActivity.class);
            intent.putExtra("NombreEmpleado", listaEmpleados[i].getFirst_name());
            intent.putExtra("ApellidoEmpleado", listaEmpleados[i].getLast_name());
            intent.putExtra("EmployeeId", listaEmpleados[i].getEmployee_id());
            intent.putExtra("api-key",api_key);
            startActivity(intent);

        }else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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



    public void borrarEmpleado(View view, String EmployeeID){

        int i = 0;
        for (int z = 0; z<listaEmpleados.length;z++){
            if(listaEmpleados[z].getEmployee_id() == idEmployee ){
                i = z;
            }
        }
        idEmployee = listaEmpleados[i].getEmployee_id();

        if(listaEmpleados[i].getCreateBy() != null){


            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/empleado";
            StringRequest stringRequest = new StringRequest(StringRequest.Method.DELETE, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> cabeceras  = new HashMap<>();
                    cabeceras.put("api-key",api_key);
                    return cabeceras;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("id", idEmployee);
                    return parametros;
                }
            };

            Intent intent = new Intent(this, EditarEmpleadoActivity.class);
            intent.putExtra("NombreEmpleado", listaEmpleados[i].getFirst_name());
            intent.putExtra("ApellidoEmpleado", listaEmpleados[i].getLast_name());
            intent.putExtra("EmployeeId", listaEmpleados[i].getEmployee_id());
            intent.putExtra("api-key",api_key);
            startActivity(intent);

        }else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Acceso Degenado");
            alertDialog.setMessage("No se puede borrar este trabajo porque no fue creado por usted");
            alertDialog.setPositiveButton("De Acuerto", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }




    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.listEmployee :
                Intent intent =new Intent( this,ListarEmpleadosActivity.class);
                startActivity(intent);
                return true;

            case R.id.addWork :

                return true;


        }
        return super.onOptionsItemSelected(item);

    }
}


