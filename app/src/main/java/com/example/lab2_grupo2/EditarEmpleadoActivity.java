package com.example.lab2_grupo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2_grupo2.Entidades2.Trabajo;
import com.example.lab2_grupo2.Entidades2.Empleado;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class EditarEmpleadoActivity extends AppCompatActivity {
    Empleado empleado = new Empleado();
    String api_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);

        Intent intent = getIntent();

        TextView textView1 = findViewById(R.id.editNombreEmpleado);
        TextView textView2 = findViewById(R.id.editApellidoEmpleado);
        TextView textView3 = findViewById(R.id.editCorreoElectronico);
        TextView textView4 = findViewById(R.id.editNumeroCelular);
        TextView textView5 = findViewById(R.id.editSalario);
        TextView textView6 = findViewById(R.id.editComision);
        TextView textView7 = findViewById(R.id.textViewId);

        String nombre = intent.getStringExtra("NombreEmpleado");
        String apellido = intent.getStringExtra( "ApellidoEmpleado");
        String correo = intent.getStringExtra ("CorreoElectronico");
        String celular = intent.getStringExtra("NumeroCelular");
        int salario = intent.getIntExtra("salario",0);
        int comision = intent.getIntExtra("comision",0);
        String id = intent.getStringExtra("EmployeeId");

        textView1.setText(nombre);
        textView2.setText(apellido);
        textView3.setText(correo);
        textView4.setText(celular);
        textView5.setText(String.valueOf(salario));
        textView6.setText(String.valueOf(comision));
        textView7.setText(id);

    }

    public void guardarEmpleado(View view){

        Intent intent = getIntent();

        TextView textView1 = findViewById(R.id.editNombreEmpleado);
        TextView textView2 = findViewById(R.id.editApellidoEmpleado);
        TextView textView3 = findViewById(R.id.editCorreoElectronico);
        TextView textView4 = findViewById(R.id.editNumeroCelular);
        TextView textView5 = findViewById(R.id.editSalario);
        TextView textView6 = findViewById(R.id.editComision);
        TextView textView7 = findViewById(R.id.textViewId);

        String nombre = intent.getStringExtra("NombreEmpleado");
        String apellido = intent.getStringExtra( "ApellidoEmpleado");
        String correo = intent.getStringExtra ("CorreoElectronico");
        String celular = intent.getStringExtra("NumeroCelular");
        int salario = intent.getIntExtra("salario",0);
        int comision = intent.getIntExtra("comision",0);
        String idEmployee = intent.getStringExtra("EmployeeId");
        api_key = intent.getStringExtra("api-key");

        empleado.setEmployee_id(idEmployee);
        empleado.setFirst_name(nombre);
        empleado.setLast_name(apellido);
        empleado.setCorreoElectronico(correo);
        empleado.setNumeroCelular(celular);
        empleado.setSalario(salario);
        empleado.setSalario(comision);
        empleado.setUpdate("true");



        String urlEditar = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/empleado";
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, urlEditar, new Response.Listener<String>() {
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
                String json = gson.toJson(empleado);
                return json;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void vistaEditarTrabajo(View view, int i){

        Empleado[] empleado = null;
        String[] createBy = null;
        if(createBy[i] != null){
            Intent intent = new Intent(this, EditarEmpleadoActivity.class);
            intent.putExtra("NombreEmpleado", empleado[i].getFirst_name());
            intent.putExtra("ApellidoEmpleado", empleado[i].getLast_name());
            intent.putExtra("CorreoElectronico", empleado[i].getCorreoElectronico());
            intent.putExtra("NumeroCelular", empleado[i].getNumeroCelular());
            intent.putExtra("salario", empleado[i].getSalario());
            intent.putExtra("comision", empleado[i].getComision());
            intent.putExtra("EmployeeId", empleado[i].getEmployee_id());
            intent.putExtra("api-key",api_key);
            startActivity(intent);

        }else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Acceso Degenado");
            alertDialog.setMessage("No puede editar este trabajo ya que no fue creado por usted");
            alertDialog.setPositiveButton("De Acuerto", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
    }

}
