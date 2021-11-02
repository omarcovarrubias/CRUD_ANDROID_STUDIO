package com.example.tiendita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiconfig {
    @GET("busqueda.php")
    Call<List <Productos>>getContact(
            @Query("item_type")String item_type,
            @Query("key")String Keyword
    );
}
