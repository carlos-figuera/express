package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.expres_cadetes_espartadev.expres_cadetes.Adaptadores.Adaptador_Item_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_id_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Presentador.Presentador_crear_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Crear_Ruta extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView Rv_Rutas;
    Adaptador_Item_ruta adaptador_item_ruta;
    Button Btn_guardar;
    private BD_Express bd_express;//Instancia de la base de datos
    int tu = 0;
TextView text_json;
    Presentador_crear_ruta presentador_crear_ruta;

    public Crear_Ruta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ruta, container, false);
        bd_express = new BD_Express(getActivity(), "BD_EXPRESS", null, 1);
        Rv_Rutas = (RecyclerView) view.findViewById(R.id.Rv_Rutas);
        Rv_Rutas.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador_item_ruta = new Adaptador_Item_ruta(bd_express.get_Items_de_ruta(), Rv_Rutas);
        Rv_Rutas.setAdapter(adaptador_item_ruta);
        adaptador_item_ruta.notifyDataSetChanged();

        Btn_guardar = (Button) view.findViewById(R.id.Btn_guardar);
        Btn_guardar.setOnClickListener(this);
        // Toast.makeText(getActivity(), "Tarea cancelada!",Toast.LENGTH_SHORT).show();
        presentador_crear_ruta = new Presentador_crear_ruta(getActivity());
        //Toast.makeText(getActivity(), "" + toJSon("10", lista_rutas()), Toast.LENGTH_SHORT).show();
        Log.e("Resulados: ", toJSon("10", lista_rutas()));
        text_json= (TextView) view.findViewById(R.id.text_json);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(getActivity(), "Tarea cancelada!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    public List<Mo_id_ruta> lista_rutas() {
        List<Mo_Items_ruta> list = new ArrayList<>();
        List<Mo_id_ruta> id_pro = new ArrayList<>();
        list = bd_express.get_Items_de_ruta();


        if (list != null) {


            for (int i = 0; i < list.size(); i++) {
                id_pro.add(new Mo_id_ruta(list.get(i).getID()));

            }
            //String json = new Gson().toJson(id_pro);
          //  Toast.makeText(getActivity(), "dd" + json, Toast.LENGTH_SHORT).show();
            // Log.e("Jsonformat", json);


        }

        return id_pro;
    }


    public static String toJSon(String cadete_id, List<Mo_id_ruta> ids) {
        try {
            // Here we convert Java Object to JSON

            JSONObject jsonObj = new JSONObject();
            // Set the first name/pair
            jsonObj.put("cadete_id", cadete_id);

            String [] id = new String[ids.size()];
            for (int i = 0; i < ids.size(); i++) {

                id [i] =ids.get(i).getId();

            }
             
            jsonObj.put("products", Arrays.toString(id));

    /*
            JSONObject jsonDATA = new JSONObject();

            // We add the object to the main object
            jsonDATA.put("data", jsonObj);
*/

            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btn_guardar:

                presentador_crear_ruta.Ruta_terminada(datos());
                break;
        }

    }


    public Map<String, String> datos() {
        Map<String, String> parametro = new HashMap<String, String>();
        parametro.put("data", toJSon("10", lista_rutas()));
        return parametro;
    }// esta funsion devuelve un Mapa de datos que se le pasa como argumento al metodo regitrar que  esta en el presentador de registros

}
