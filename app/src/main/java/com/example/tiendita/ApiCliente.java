package com.example.tiendita;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {
    public static final String BASE_URL="https://dominiopruebaandroid.000webhostapp.com/tiendita/busqueda/";
    public static Retrofit retrofit;
    public static Retrofit getCliente(){
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
