package com.example.recyclerview.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LugarModelo {

    public LugarModelo(JSONObject a) throws JSONException {
        Nombre =  a.getString("nombre_lugar").toString() ;

        Direccion =  a.getString("direccion").toString() ;
        Telefono =  a.getString("telefono").toString() ;
        UrlLogo = "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/" + a.getString("logo").toString() ;
    }
    public static ArrayList<LugarModelo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<LugarModelo> listalugares = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            listalugares.add(new LugarModelo(datos.getJSONObject(i)));
        }
        return listalugares;
    }

    String Nombre, Direccion, Telefono, UrlLogo;
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getUrlLOgo() {
        return UrlLogo;
    }

    public void setUrlLOgo(String urlLOgo) {
        UrlLogo = urlLOgo;
    }
}
