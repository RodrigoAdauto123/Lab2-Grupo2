package com.example.lab2_grupo2;

import androidx.appcompat.app.AppCompatActivity;

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

public class CrearTrabajoActivity extends AppCompatActivity {

    String[] listaNombresDepart = new String[0];
    String[] listaShortName = new String[0];
    Departamento[] lista;
    String job_id;
    String nombreTrabajo;
    String SalMaxTrabajo;
    String SalMinTrabajo;
    String api_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey?groupKey=v4NYj9Ft4AHs95S9Qbvu";

        StringRequest stringRequest  = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("exitoConsulta",response);
                Gson gson = new Gson();
                ApiKey apikey = gson.fromJson(response,ApiKey.class);
                api_key = apikey.getApiKey();


                String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";
                StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DepartamentoDTO dDTO = gson.fromJson(response,DepartamentoDTO.class);
                        //LIsta de departamentos
                        lista = dDTO.getLista();
                        for (int i=0;i<lista.length;i++){
                            listaNombresDepart[i] = lista[i].getDepartmentName();
                            listaShortName[i] = lista[i].getDepartmentShortName();
                        }

                        Spinner spinner = (Spinner) findViewById(R.id.spinnerDepartamento);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearTrabajoActivity.this,android.R.layout.simple_spinner_dropdown_item,
                                listaNombresDepart);
                        spinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorConsulta","Ocurrio un error");
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> cabeceras = new HashMap<>();
                        cabeceras.put("api-key",api_key);
                        return cabeceras;
                    }
                };



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

    public void Crear(View view){

        EditText editText1 = findViewById(R.id.editTextNombreTrabajo);
        EditText editText2 = findViewById(R.id.editTextSalMaxTrabajo);
        EditText editText3 = findViewById(R.id.editTextSalMinTrabajo);
        EditText editText4 = findViewById(R.id.editTextAbrevTrabajo);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerDepartamento);
        String departamento = spinner.getSelectedItem().toString();

        nombreTrabajo = editText1.getText().toString();
        SalMaxTrabajo = editText2.getText().toString();
        SalMinTrabajo = editText3.getText().toString();
        String AbrevTrabajo = editText4.getText().toString();

        for (int i=0;i<lista.length;i++){

            if(listaNombresDepart[i] == departamento){
                job_id = listaShortName[i] + "_"+ AbrevTrabajo;
            }
        }

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
/**
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("job_id",job_id);
                params.put("jobTitle",nombreTrabajo);
                params.put("minSalary",SalMinTrabajo);
                params.put("maxSalary",SalMaxTrabajo);
                params.put("update","true");
                return params;

            }
*/
            @Override
            public String getBodyContentType() {

                Gson gson = new Gson();
                Trabajo trabajo = new Trabajo();
                trabajo.setJob_id(job_id);
                trabajo.setJobTitle(nombreTrabajo);
                trabajo.setMaxSalary(Integer.valueOf(SalMaxTrabajo));
                trabajo.setMinSalary(Integer.valueOf(SalMinTrabajo));
                String json = gson.toJson(trabajo);
                return json;

            //    return "application/x-www-form-urlencoded; charset=UTF-8";

            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}
