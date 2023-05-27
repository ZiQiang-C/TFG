package com.example.degabriel.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class datoCarrito {
    @SerializedName("Descripcion")
    @Expose
    private String Descripcion;

    @SerializedName("Imagen")
    @Expose
    private List<String>  Imagen;

    @SerializedName("Nombre")
    @Expose
    private String  Nombre;

    @SerializedName("Precio")
    @Expose
    private String  Precio;

    @SerializedName("Stock")
    @Expose
    private String  Stock;

    public List<String> getImagen() {
        return Imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public String getStock() {
        return Stock;
    }
}
