package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expres_cadetes_espartadev.expres_cadetes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class container_crear_ruta extends Fragment implements View.OnClickListener {

    FragmentManager fragmentManager;
    static Context context;

    private ViewPager mViewPager;
    TabLayout tabLayout;
    View view;

    LinearLayout L_Ruta,L_mapa;
    TextView txt_linea_mapa,txt_linea_ruta;

    public container_crear_ruta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_container_crear_ruta, container, false);
        context= getActivity();

        txt_linea_mapa=(TextView) view.findViewById(R.id.txt_linea_mapa);
        txt_linea_ruta=(TextView) view.findViewById(R.id.txt_linea_ruta);
        L_mapa=(LinearLayout)view.findViewById(R.id.L_mapa);
        L_mapa.setOnClickListener(this);
        L_Ruta=(LinearLayout)view.findViewById(R.id.L_Ruta);
        L_Ruta.setOnClickListener(this);
        txt_linea_mapa.setVisibility(View.VISIBLE);
        txt_linea_ruta.setVisibility(View.INVISIBLE);

        fragmentManager=getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.PARTE1, new Mapa()).commit();
        return view;


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.L_mapa:
                txt_linea_mapa.setVisibility(View.VISIBLE);
                txt_linea_ruta.setVisibility(View.INVISIBLE);
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.PARTE1, new Mapa()).commit();


                break;
            case R.id.L_Ruta:
                txt_linea_mapa.setVisibility(View.INVISIBLE);
                txt_linea_ruta.setVisibility(View.VISIBLE);
                fragmentManager=getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.PARTE1, new Crear_Ruta()).commit();
                break;
        }

    }
}
