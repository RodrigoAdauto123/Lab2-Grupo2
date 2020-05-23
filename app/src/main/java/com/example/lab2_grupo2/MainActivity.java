package com.example.lab2_grupo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2_grupo2.entidades.ApiKey;
import com.example.lab2_grupo2.entidades.Departamento;
import com.example.lab2_grupo2.entidades.DepartamentoDTO;
import com.example.lab2_grupo2.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String api_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey?groupKey=v4NYj9Ft4AHs95S9Qbvu";

        StringRequest stringRequest  = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("exitoConsulta",response);
                Gson gson = new Gson();
                ApiKey apikey = gson.fromJson(response,ApiKey.class);
                api_key = apikey.getApiKey();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorConsulta","Ocurrio un error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("groupKey","v4NYj9Ft4AHs95S9Qbvu");
                return parametros;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }




    public void vistaEditarTrabajo(View view, int i){

    Trabajo[] trabajo = null;
    String[] createBy = null;
    if(createBy[i] != null){
        Intent intent = new Intent(this,EditarTrabajoActivity.class);
        intent.putExtra("NombreTrabajo",trabajo[i].getJobTitle());
        intent.putExtra("SalarioMaximo",trabajo[i].getMaxSalary());
        intent.putExtra("SalarioMinimo",trabajo[i].getMinSalary());
        intent.putExtra("JobId",trabajo[i].getJob_id());
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


}
