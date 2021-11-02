package com.example.tiendita;

import com.google.gson.annotations.SerializedName;

public class Productos {
    @SerializedName("idproducto") String idproducto;
    @SerializedName("codigo") String codigo;
    @SerializedName("descripcion") String descripcion;

    public String getIdproducto() {
        return idproducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
