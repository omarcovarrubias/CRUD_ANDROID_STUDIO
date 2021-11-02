package com.example.tiendita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarProducto extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Productos>productos;
    private Adaptador adaptador;
    private Apiconfig apiconfig;
    private ProgressBar progressBar;
    String[] item;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        searchView=findViewById(R.id.sc);
        recyclerView.setHasFixedSize(true);
        fetchUser("datos","");
    }

    private void fetchUser(String type, String key) {
    apiconfig=ApiCliente.getCliente().create(Apiconfig.class);
    Call<List<Productos>> call=apiconfig.getContact(type,key);
    call.enqueue(new Callback<List<Productos>>() {
        @Override
        public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
            progressBar.setVisibility(View.GONE);
            productos=response.body();
            adaptador= new Adaptador(productos,BuscarProducto.this);
            recyclerView.setAdapter(adaptador);

        }

        @Override
        public void onFailure(Call<List<Productos>> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
            Toast.makeText(BuscarProducto.this,"Error al cargar los productos",Toast.LENGTH_SHORT).show();
        }
    });
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            fetchUser("datos",query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            fetchUser("datos",newText);
            return false;
        }
    });
    }
}