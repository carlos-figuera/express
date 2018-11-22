package com.expres_cadetes_espartadev.expres_cadetes.Presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Todos_los_Servicios;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;
import com.expres_cadetes_espartadev.expres_cadetes.Volley_singleton.Volley_singleton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presentado_mapas {

    Context context;
    private BD_Express bd_express;//Instancia de la base de datos
    public Presentado_mapas(Context context) {
        this.context = context;
    }
    String nombre="marker";
    public void Consultar_marcadores(String Url, final String localidad, final GoogleMap mMap) {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        bd_express.borrar_TEMP("Todos_Servicio");

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("cargando...");
        progressDialog.setCancelable(false);

        String url = Url;//        ;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
               // Toast.makeText(context, ""+ response.toString(), Toast.LENGTH_SHORT).show();


                if (response != null) {

                           JSONObject clientObject = null;

                    try {

                        JSONArray jsonArray = response.getJSONArray("data");
                        Toast.makeText(context, "tpotal " + jsonArray.length(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String ent_id = jsonObject.getString("ent_id");
                            String name = jsonObject.getString("name");
                            String serie = jsonObject.getString("serie");
                            String state = jsonObject.getString("state");
                            String location = jsonObject.getString("location");
                            String address = jsonObject.getString("address");
                            String cp = jsonObject.getString("cp");
                            String status = jsonObject.getString("status");
                            String client_id = jsonObject.getString("client_id");
                            String x = jsonObject.getString("x");
                            String y = jsonObject.getString("y");
                            bd_express.Guardar_Todos_servicios(id,ent_id,name,serie,state,location,address,cp,status,client_id,x,y);

                            clientObject = jsonObject.getJSONObject("client");




                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                Crear_marcador(mMap);
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }

        })

        {

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("location",localidad);


                return params;
            }
        };

        Volley_singleton.getInstancia_volley(context).addToRequestQueue(request);
        //quere.add(request);
    }


    public void Crear_marcador(GoogleMap mMap) {
        List<Mo_Todos_los_Servicios> lista = new ArrayList<>();
        lista = bd_express.get_Todos_servicios();
        String nombre="marker";
        LatLng dato;
        for (int i = 0; i < lista.size(); i++) {

            dato = new LatLng(Double.parseDouble(lista.get(i).getX()), Double.parseDouble(lista.get(i).getY()));
            Marker  marker = mMap.addMarker(new MarkerOptions()
                    .draggable(true).position(dato)
                    .title(lista.get(i).getName()+"&"+lista.get(i).getEnt_id()+"&"+lista.get(i).getId()+"&"+lista.get(i).getStatus()+"&"+lista.get(i).getAddress()  )
                    .snippet(lista.get(i).getAddress()));
            marker.setTag(lista.get(i).getId());
            marker_select(marker);
        }


        LatLng dato1 = new LatLng(Double.parseDouble(lista.get(0).getX()), Double.parseDouble(lista.get(0).getY()));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(dato1)
                .zoom(15)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //Toast.makeText(getActivity(), "con el primer  marcador", Toast.LENGTH_SHORT).show();


    }


    public void marker_select(Marker marker) {

        if (marker != null) {
            String[] info = marker.getTitle().split("&");



            if (info[3].equals("NEGATIVO")){
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            }
            else if (info[3].equals("0-RECUPERADO")){
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }

        }//Cambia el color del marcador cuando se sellecionas

    }



}
