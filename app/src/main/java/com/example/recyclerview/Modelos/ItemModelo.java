package com.example.recyclerview.Modelos;

import androidx.annotation.NonNull;

public class ItemModelo {

    int ID;
    String Descripcion;

    @NonNull
    @Override
    public String toString() {
        return Descripcion;
    }

    public ItemModelo(int ID, String descripcion) {
        this.ID = ID;
        Descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}