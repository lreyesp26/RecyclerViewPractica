package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.recyclerview.Adaptadores.LugarAdaptador;
import com.example.recyclerview.Listeners.FillCbListener;
import com.example.recyclerview.Modelos.ItemModelo;
import com.example.recyclerview.Modelos.LugarModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask,
        AdapterView.OnItemSelectedListener{
    Map<String, String> datosLugares = new HashMap<String, String>();
    Spinner cbCategoria, cbSUbCategoria;
    RecyclerView rvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLista = findViewById(R.id.rvLista);
        cbCategoria = findViewById(R.id.cbCategoria);
        cbSUbCategoria = findViewById(R.id.cbSubCategoria);
        cbCategoria.setOnItemSelectedListener(this);
        cbSUbCategoria.setOnItemSelectedListener(this);

        WebService ws= new WebService(
                "https://uealecpeterson.net/turismo/categoria/getlistadoCB",
                datosLugares, MainActivity.this, new FillCbListener(cbCategoria, "id", "descripcion", true));
        ws.execute("GET");

        rvLista.setHasFixedSize(true);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int IDCat=0, IDSubCat=0;
        int IDItemSeleccionado = ((ItemModelo)parent.getItemAtPosition(position)).getID();
        if(parent == cbCategoria) {
            if (IDItemSeleccionado>0){
                IDCat = IDItemSeleccionado;
                WebService ws2= new WebService(
                        "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + IDCat,
                        datosLugares, MainActivity.this,
                        new FillCbListener(cbSUbCategoria, "id", "descripcion", true) );
                ws2.execute("GET");
            }

        }else if (parent == cbSUbCategoria){
            if (cbCategoria.getSelectedItemPosition()!=AdapterView.INVALID_POSITION){
                IDCat = ((ItemModelo)cbCategoria.getSelectedItem()).getID();
                IDSubCat = IDItemSeleccionado;
            }
        }

        WebService ws3 = new WebService(
                "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/" +
                        IDCat + "/" + IDSubCat,
                datosLugares, MainActivity.this, MainActivity.this);
        ws3.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<LugarModelo> lstLugares;

        JSONObject JSONlista =  new JSONObject(result);
        JSONArray JSONlistaLugares=  JSONlista.getJSONArray("data");
        lstLugares = LugarModelo.JsonObjectsBuild(JSONlistaLugares);

        LugarAdaptador adaptadorLugaresT = new LugarAdaptador(this, lstLugares);
        rvLista.setAdapter(adaptadorLugaresT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}