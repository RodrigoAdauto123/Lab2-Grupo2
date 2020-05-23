package com.example.lab2_grupo2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.lab2_grupo2.entidades.Trabajo;
import com.example.lab2_grupo2.entitades.DtoTrabajo;
import com.example.lab2_grupo2.entitades.TrabajoListar;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String api_key;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appbar, menu);

        return true;
    }

    /**@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteBar:
                Toast.makeText(this, "deleeteBar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.infoBar:
                Toast.makeText(this, "infoBar", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void accionElminarAppBar(MenuItem item) {
        View menuView = findViewById(item.getItemId());
        PopupMenu popupMenu = new PopupMenu(this, menuView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                 @Override
                                                 public boolean onMenuItemClick(MenuItem item) {
                                                     switch (item.getItemId()) {
                                                         case R.id.eliminarElemento:
                                                             AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                                                             dialog.setMessage("Estás seguro que deseas eliminar todos los elementos");
                                                             dialog.setPositiveButton("Eliminar todo", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialogInterface, int i) {
                                                                     Toast.makeText(MainActivity.this,"Eliminar",Toast.LENGTH_SHORT).show();

                                                                 }

                                                             });
                                                                     dialog.setNegativeButton("no Aceptar", new DialogInterface.OnClickListener() {
                                                                         @Override
                                                                         public void onClick(DialogInterface dialogInterface, int i) {

                                                                         }
                                                                     });
                                                                     dialog.show();
                                                         case R.id.eliminarTodo:
                                                             Toast.makeText(MainActivity.this, "elminarAll", Toast.LENGTH_SHORT).show();
                                                         default:
                                                             return false;

                                                     }


                                                 }
                                             }


        );
        popupMenu.show();


    }*/
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

        String url="http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";
        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Gson gson=new Gson();
                        DtoTrabajo dtoTrabajo= gson.fromJson(response, DtoTrabajo.class);
                        TrabajoListar[] lista=dtoTrabajo.getTrabajoListars();
                        ListaTrabajosAdapter listaTrabajosAdapter =new ListaTrabajosAdapter(lista, MainActivity.this);
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setAdapter(listaTrabajosAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



/**String fileName="listaDeEmpleados";
 try (FileOutputStream outputStream = MainActivity.this.openFileOutput(fileName,Context.MODE_PRIVATE);
 FileWriter fileWriter =new FileWriter(outputStream.getFD());){
 String listaEmpleadosAsJson=gson.toJson(listaEmpleados);
 fileWriter.write(listaEmpleadosAsJson);

 } catch (IOException e) {
 e.printStackTrace();
 }
 */
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras =new HashMap<>();
                cabeceras.put("api-key","TPyPr3quVcAK8UYSGq6c");
                return cabeceras;


            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    public void borrarTrabajo(View view, final int i){



        final Trabajo[] trabajo = null;
        String[] createBy = null;

        if(createBy[i] != null){

            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/trabajo";
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
                    parametros.put("id",trabajo[i].getJob_id());
                    return parametros;
                }
            };

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
            alertDialog.setMessage("No se puede borrar este trabajo porque no fue creado por usted");
            alertDialog.setPositiveButton("De Acuerto", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }




    }

}
