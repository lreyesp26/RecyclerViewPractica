package com.example.recyclerview.Listeners;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.recyclerview.Modelos.ItemModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import WebServices.Asynchtask;

public class FillCbListener implements Asynchtask {
    Spinner cb;
    String campoID, campoDesc;
    Boolean TodosItems;
    public FillCbListener(Spinner cb, String campoID, String campoDesc, Boolean todosItems) {
        this.cb = cb;
        this.campoID = campoID;
        this.campoDesc = campoDesc;
        TodosItems = todosItems;
    }

    @Override    public void processFinish(String result) throws JSONException {
        ArrayList<ItemModelo> datos = new ArrayList<ItemModelo>();
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject lugar=  JSONlista.getJSONObject(i);
            datos.add( new ItemModelo(lugar.getInt(campoID),
                    lugar.getString(campoDesc)));
        }

        ArrayAdapter<ItemModelo> adaptador =
                new ArrayAdapter<ItemModelo>(cb.getContext(),
                        android.R.layout.simple_spinner_item, datos);


        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cb.setAdapter(adaptador);
    }
}