package com.example.lab2_grupo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2_grupo2.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class EditarTrabajoActivity extends AppCompatActivity {
    Trabajo trabajo = new Trabajo();
    String api_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_trabajo);

        Intent intent = getIntent();

        TextView textView = findViewById(R.id.editNombreTrabajo3);
        TextView textView2 = findViewById(R.id.editSalMinTrabajo3);
        TextView textView3 = findViewById(R.id.editSalMaxTrabajo3);
        TextView textView4 = findViewById(R.id.textViewId);

        String nombre = intent.getStringExtra("NombreTrabajo");
        int SalarioMaximo = intent.getIntExtra("SalarioMaximo",0);
        int SalarioMinimo = intent.getIntExtra("SalarioMinimo",0);
        String id = intent.getStringExtra("JobId");

        textView.setText(nombre);
        textView2.setText(String.valueOf(SalarioMaximo));
        textView3.setText(String.valueOf(SalarioMinimo));
        textView4.setText(id);
    }


    public void guardarTrabajo(View view){

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.editNombreTrabajo3);
        TextView textView2 = findViewById(R.id.editSalMinTrabajo3);
        TextView textView3 = findViewById(R.id.editSalMaxTrabajo3);
        TextView textView4 = findViewById(R.id.textViewId);

        String nombre = intent.getStringExtra("NombreTrabajo");
        int SalarioMaximo = intent.getIntExtra("SalarioMaximo",0);
        int SalarioMinimo = intent.getIntExtra("SalarioMinimo",0);
        String id = intent.getStringExtra("JobId");
        api_key = intent.getStringExtra("api-key");

        trabajo.setJob_id(id);
        trabajo.setJobTitle(nombre);
        trabajo.setMaxSalary(SalarioMaximo);
        trabajo.setMinSalary(SalarioMinimo);
        trabajo.setUpdate("true");



        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/trabajo";
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
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
                Map<String, String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",api_key);
                return cabeceras;
            }

            @Override
            public String getBodyContentType() {

                Gson gson = new Gson();
                String json = gson.toJson(trabajo);
                return json;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
