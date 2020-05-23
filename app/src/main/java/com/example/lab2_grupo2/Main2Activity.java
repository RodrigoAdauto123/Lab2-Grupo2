package com.example.lab2_grupo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lab2_grupo2.entitades.DtoEmpleado;
import com.example.lab2_grupo2.entitades.Empleado;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    static String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


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
    public void obtenerDeInternetApiKEy(View view) {

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey";
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,

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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorVol", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                View view1=findViewById(R.id.ObtenerAPIKEYButton);
                String groupKey=view1.toString();
                Map<String, String> cabeceras = new HashMap<>();
                cabeceras.put("api-key", groupKey);
                return cabeceras;
            }
        };

    } }


