    package com.example.tiendita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

    public class BuscarEditar extends AppCompatActivity {
        RequestQueue requestQueue;
        EditText idproducto,descripcion,codigo;
        Button btnBuscarEditar,btnCancelar,btnEditar,btnEliminar;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {


        // super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_buscar_editar); Estas dos sentencias deben estar en orden , una abajo de la otra , no al revez , No ejecutaba una peticion porque la variable estaba abajo con las variables invocadas de los botones
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_editar);


        idproducto=(EditText)findViewById(R.id.editarid);
        descripcion=(EditText)findViewById(R.id.editard);
        codigo=(EditText) findViewById(R.id.editarc);

        //Las variables de los componentes deben ir abajo de la linea de codigo setContentView(R.layout.activity_buscar_editar); , "Si , Asi es el pto android studio J0dete"
        //Las pruebas que realize fueron con el pto boton

        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editarproducto("https://dominiopruebaandroid.000webhostapp.com/tiendita/busqueda/editar_producto.php");

            }
        });



        btnCancelar=(Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(BuscarEditar.this,MainActivity.class);
                startActivity(i);
            }
        });


        btnBuscarEditar=(Button)findViewById(R.id.btnEditarBuscar);
        btnBuscarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarproducto("https://dominiopruebaandroid.000webhostapp.com/tiendita/busqueda/editarBusqueda.php?idproducto="+idproducto.getText()+"");

            }
        });

        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarproducto("https://dominiopruebaandroid.000webhostapp.com/tiendita/busqueda/eliminar_producto.php");

            }
        });
        String productoid= idproducto.getText().toString().trim();
        ProgressDialog progressDialog=new ProgressDialog(this);

        if(productoid.isEmpty())
        {
            idproducto.setError("Ingresa el codigo del producto :D");
        }

    }



        public void buscarproducto(String URL){



         JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        idproducto.setText(jsonObject.getString("idproducto"));
                        codigo.setText(jsonObject.getString("codigo"));
                        descripcion.setText(jsonObject.getString("descripcion"));
                        //idproducto.setVisibility(View.GONE);
                        idproducto.setEnabled(false);



                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
           });
            requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(jsonArrayRequest);

    }




        //metodo para envar las peticiones al servidor
        private void editarproducto(String URL) {
            //declara una peticion
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Actualizacion exitosa", Toast.LENGTH_LONG).show();
                }

                //en caso de que algo salio mal
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                //indicar los parametros que vamos a enviar

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    //enviar los parametros (tomar en cuenta el orden en como entan en el PHP)
                    parametros.put("idproducto",idproducto.getText().toString());
                    parametros.put("codigo", codigo.getText().toString());
                    parametros.put("descripcion", descripcion.getText().toString());
                    return parametros;            }
            };

            //procesar la peticiones hechas por nuestra app

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }




        private void eliminarproducto(String URL) {
            //declara una peticion
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Eliminacion exitosa", Toast.LENGTH_LONG).show();
                }

                //en caso de que algo salio mal
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                //indicar los parametros que vamos a enviar

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    //enviar los parametros (tomar en cuenta el orden en como entan en el PHP)
                    parametros.put("idproducto", idproducto.getText().toString());
                    return  parametros;
                }
            };

            //procesar la peticiones hechas por nuestra app
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
}