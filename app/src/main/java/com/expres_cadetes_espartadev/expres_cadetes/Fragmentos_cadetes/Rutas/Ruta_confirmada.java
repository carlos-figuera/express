package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expres_cadetes_espartadev.expres_cadetes.Adaptadores.Adaptador_Item_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Adaptadores.Adaptador_Item_ruta_confirmada;
import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;


public class Ruta_confirmada extends Fragment {

View view;
    RecyclerView Rv_Rutas_confirmada;
    Adaptador_Item_ruta_confirmada adaptador_item_ruta_confirmada;
    private BD_Express bd_express;//Instancia de la base de datos
    public Ruta_confirmada() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_ruta_confirmada, container, false);
        bd_express = new BD_Express(getActivity(), "BD_EXPRESS", null, 1);
        Rv_Rutas_confirmada = (RecyclerView) view.findViewById(R.id.Rv_Rutas);
        Rv_Rutas_confirmada.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador_item_ruta_confirmada = new Adaptador_Item_ruta_confirmada(bd_express.get_Items_de_ruta(),Rv_Rutas_confirmada);
        Rv_Rutas_confirmada.setAdapter(adaptador_item_ruta_confirmada);
        adaptador_item_ruta_confirmada.notifyDataSetChanged();

        return  view;
    }




}
