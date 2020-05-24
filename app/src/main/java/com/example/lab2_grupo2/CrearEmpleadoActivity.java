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
import com.example.lab2_grupo2.Entidades2.Departamento;
import com.example.lab2_grupo2.Entidades2.DepartamentoDTO;
import com.example.lab2_grupo2.Entidades2.EmpleadoDTO;
import com.example.lab2_grupo2.Entidades2.Empleado;
import com.example.lab2_grupo2.Entidades2.TrabajoDTO;
import com.example.lab2_grupo2.Entidades2.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class CrearEmpleadoActivity extends AppCompatActivity {
    String employee_id;
    String nombreEmpleado;
    String apellidoEmpleado;
    String correoEmpleado;
    String celular;
    String salario;
    String comision;
    String api_key = " ";

    Trabajo[] listaTrabajos;
    String[] listaNombresTrabajo = new String[0];
    Empleado[] listaEmpleados;
    String[] listaNombreEmpleados = new String[0];
    Departamento[] listaDepartamento;
    String[] listaNombresDepart = new String[0];
    String[] listaShortName = new String[0];


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_crear_empleado);

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey?groupKey=v4NYj9Ft4AHs95S9Qbvu";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("exitoConsulta", response);
                Gson gson = new Gson();
                ApiKey apikey = gson.fromJson(response, ApiKey.class);
                api_key = apikey.getApiKey();

//////////////////////////// ELEGIR DEPARTAMENTO
                String url2 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";
                StringRequest stringRequest2 = new StringRequest(StringRequest.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DepartamentoDTO dDTO = gson.fromJson(response, DepartamentoDTO.class);
                        //LIsta de departamentos
                        listaDepartamento = dDTO.getLista();
                        for (int i = 0; i < listaDepartamento.length; i++) {
                            listaNombresDepart[i] = listaDepartamento[i].getDepartmentName();
                            listaShortName[i] = listaDepartamento[i].getDepartmentShortName();
                        }

                        Spinner spinner = (Spinner) findViewById(R.id.spinnerJefe);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearEmpleadoActivity.this, android.R.layout.simple_spinner_dropdown_item,
                                listaNombresDepart);
                        spinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorConsulta", "Ocurrio un error");
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> cabeceras = new HashMap<>();
                        cabeceras.put("api-key", api_key);
                        return cabeceras;
                    }
                };
                ///////////////////////ELEGIR TRABAJO
                String url3 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";
                StringRequest stringRequest3 = new StringRequest(StringRequest.Method.GET, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        TrabajoDTO trabajoDTO = gson.fromJson(response, TrabajoDTO.class);
                        listaTrabajos = trabajoDTO.getLista();
                        for (int i = 0; i < listaTrabajos.length; i++) {
                            listaNombresTrabajo[i] = listaTrabajos[i].getJobTitle();

                        }

                        Spinner spinner = (Spinner) findViewById(R.id.spinnerTrabajo);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearEmpleadoActivity.this, android.R.layout.simple_spinner_dropdown_item,
                                listaNombresDepart);
                        spinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorConsulta", "Ocurrio un error");
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> cabeceras = new HashMap<>();
                        cabeceras.put("api-key", api_key);
                        return cabeceras;
                    }
                };
//////////////// ELEGIR JEFE

                String url4 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";
                StringRequest stringRequest4 = new StringRequest(StringRequest.Method.GET, url4, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        EmpleadoDTO empleadoDTO = gson.fromJson(response, EmpleadoDTO.class);
                        listaEmpleados = empleadoDTO.getLista();
                        for (int i = 0; i < listaEmpleados.length; i++) {
                            listaNombreEmpleados[i] = listaEmpleados[i].getFirst_name() + listaEmpleados[i].getLast_name();

                        }

                        Spinner spinner = (Spinner) findViewById(R.id.spinnerJefe);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearEmpleadoActivity.this, android.R.layout.simple_spinner_dropdown_item,
                                listaNombreEmpleados);
                        spinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorConsulta", "Ocurrio un error");
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> cabeceras = new HashMap<>();
                        cabeceras.put("api-key", api_key);
                        return cabeceras;
                    }
                };


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorConsulta", "Ocurrio un error");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("groupKey", "v4NYj9Ft4AHs95S9Qbvu");
                return parametros;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
// CODIGO DEL FORMULARIO

        public void crearEmpleado (View view){

            EditText editText1 = findViewById(R.id.editTextCorreoElectronico);
            EditText editText2 = findViewById(R.id.editTextApellidoEmpleado);
            EditText editText3 = findViewById(R.id.editTextCorreoElectronico);
            EditText editText4 = findViewById(R.id.editNumeroCelular);

            Spinner spinner1 = (Spinner) findViewById(R.id.spinnerTrabajo);
            String trabajo = spinner1.getSelectedItem().toString();

            EditText editText6 = findViewById(R.id.editTextSalario);
            EditText editText7 = findViewById(R.id.editTextComision);

            Spinner spinner2 = (Spinner) findViewById(R.id.spinnerJefe);
            String jefe = spinner2.getSelectedItem().toString();

            Spinner spinner3 = (Spinner) findViewById(R.id.spinnerJefe);
            String departamento = spinner3.getSelectedItem().toString();

            nombreEmpleado = editText1.getText().toString();
            apellidoEmpleado = editText2.getText().toString();
            correoEmpleado = editText3.getText().toString();
            celular = editText4.getText().toString();
            salario = editText6.getText().toString();
            comision = editText7.getText().toString();

            int numeroId = listaEmpleados.length; // Obtenido previamente

            for (int i = 0; i < listaDepartamento.length; i++) {

                if (listaNombresDepart[i] == departamento) {
                    employee_id = numeroId + "_" + listaDepartamento[i].getDepartmentShortName();
                }
            }

            String urlx = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/empleado";
            StringRequest stringRequestX = new StringRequest(StringRequest.Method.POST, urlx, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }

            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> cabeceras = new HashMap<>();
                    cabeceras.put("api-key", api_key);
                    return cabeceras;
                }

                @Override
                public String getBodyContentType() {

                    Gson gson = new Gson();
                    Empleado empleado = new Empleado();
                    empleado.setEmployee_id(employee_id);
                    empleado.setFirst_name(nombreEmpleado);
                    empleado.setLast_name(apellidoEmpleado);
                    empleado.setCorreoElectronico(correoEmpleado);
                    empleado.setNumeroCelular(celular);
                    empleado.setSalario(Integer.valueOf(salario));
                    empleado.setComision(Integer.valueOf(comision));
                    String json = gson.toJson(empleado);
                    return json;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequestX);
        }


    }



