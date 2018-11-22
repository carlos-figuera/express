package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cliente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expres_cadetes_espartadev.expres_cadetes.Adaptadores.Adaptador_Item_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Adaptadores.Adaptador_Item_servicio_cliente;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_servicio_cliente;
import com.expres_cadetes_espartadev.expres_cadetes.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Servicios_cliente extends Fragment {
View view;
RecyclerView Rv_servicios;
Adaptador_Item_servicio_cliente adaptador_item_servicio_cliente;


    public Servicios_cliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_servicio_cliente, container, false);

        Rv_servicios=(RecyclerView)view.findViewById(R.id.Rv_servicios);
        Rv_servicios.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptador_item_servicio_cliente= new Adaptador_Item_servicio_cliente(lista_servicios());
        Rv_servicios.setAdapter(adaptador_item_servicio_cliente);
        adaptador_item_servicio_cliente.notifyDataSetChanged();
        return view;
    }




    public List<Mo_Items_servicio_cliente>lista_servicios(){
        List<Mo_Items_servicio_cliente>list= new ArrayList<>();
        for (int i=0;i<10;i++){

           list.add(new Mo_Items_servicio_cliente("Servicio"+ i,"Actividad"+ i));
        }
        return list;
    }

}
