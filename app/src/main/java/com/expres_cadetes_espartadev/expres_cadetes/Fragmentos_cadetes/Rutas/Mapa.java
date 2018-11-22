package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expres_cadetes_espartadev.expres_cadetes.Google_maps.CustomInfoWindowAdapter;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Todos_los_Servicios;
import com.expres_cadetes_espartadev.expres_cadetes.Presentador.Presentado_mapas;
import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static android.location.LocationManager.GPS_PROVIDER;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mapa extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, View.OnClickListener, GoogleMap.OnInfoWindowClickListener {
    View view;
    private GoogleMap mMap;
    Marker marcador1;
    Marker marker_sel;//Toma el valor del marcador seleccionado por el usuario
    int con = 0;//contador  para  sembrar la base de datos utilizado en el metodo de  arrastre de marcadores de mapa
    Dialog dialog;
    AlertDialog alertDialog;
    LatLng aqui;//Toma la ubicacion actual del dispositivo,  es origuinado en el metodo mi unicacion  y consumido en crear marcadores
    private BD_Express bd_express;//Instancia de la base de datos
    int REQUEST_LOCATION = 1;
    //String Url="http://espartadevs.website/express/public/api/productos?cp=9999";
    String Url="http://espartadevs.website/express/public/api/productos?location=VILLAROSA";

    Presentado_mapas presentado_mapas;
    public Mapa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mapa, container, false);

        bd_express = new BD_Express(getActivity(), "BD_EXPRESS", null, 1);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presentado_mapas= new Presentado_mapas(getContext());
      //  bd_express.borrar_TEMP("Todos_Servicio");//Borro la tabla donde  se  almacenan los servicios

        ubicacion();
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (permisos() == true) {

            //Toast.makeText(getActivity(), "totddddddddddal"+  bd_express.get_Todos_servicios().size(), Toast.LENGTH_SHORT).show();

            LatLng dato = new LatLng(10.940787, -63.883405);


            presentado_mapas.Consultar_marcadores(Url,"VILLAROSA",mMap);


            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setCompassEnabled(false);
            uiSettings.setZoomControlsEnabled(true);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {
                mMap.setMyLocationEnabled(true);
            }


            googleMap.setOnMarkerClickListener(this);
            googleMap.setOnMarkerDragListener(this);


        } else {
            Toast.makeText(getActivity(), "Permisos no otorgados", Toast.LENGTH_LONG).show();
        }

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(LayoutInflater.from(getActivity()) ,getActivity()));
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       // add_Items_Rut(marker);
        marker_sel = marker;

        return false;
    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(marcador1)) {
            con = con + 1;
            String titulo = "";

            //bd_express.Guardar_Todos_servicios("" + con, "servicio" + con, "repracion", "Activo", "cliente" + con, Double.toString(marker.getPosition().longitude), Double.toString(marker.getPosition().latitude));

        }

    }



    @Override
    public void onInfoWindowClick(Marker marker) {
            Toast.makeText(getActivity(), "Añadido a la  lista : "+ marker.getId().toString(), Toast.LENGTH_SHORT).show();
           Guardar_item_Ruta(marker);
           marker_select( marker);
           // bd_express.Guardar_Temp__servicios(marker.getTag().toString());
        //Guardar_item_Ruta(marker_sel);

    }




    public void add_Items_Rut(Marker marker) {


        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_servicio);

        TextView nombre_cli = (TextView) dialog.findViewById(R.id.Txt_nombre_cli);

        ImageView Img_Cancelar = (ImageView) dialog.findViewById(R.id.Img_Cancelar);
        Img_Cancelar.setOnClickListener(this);
        ImageView Img_add_service = (ImageView) dialog.findViewById(R.id.Img_add_service);
        Img_add_service.setOnClickListener(this);
        nombre_cli.setText(marker.getTitle().toString());

        dialog.show();

    }
    public void marker_select(Marker marker) {

        if (marker != null) {
            String[] info = marker.getTitle().split("&");
            //Toast.makeText(getActivity(), "" +info[3], Toast.LENGTH_SHORT).show();


            if (info[3].equals("NEGATIVO")){

                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.negativo1));
            }
            else if (info[3].equals("0-RECUPERADO")){

                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.recuperado1));
            }

        }//Cambia el color del marcador cuando se sellecionas

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Img_Cancelar:
                dialog.dismiss();
                break;
            case R.id.Img_add_service:
                marker_select(marker_sel);

                Toast.makeText(getActivity(), "seleccione " + marker_sel.getTag().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
        }

    }



    public Boolean permisos() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Aquí muestras confirmación explicativa al usuario
                // por si rechazó los permisos anteriormente
            } else {

                ActivityCompat.requestPermissions(
                        getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION);
            }
        } else {

            LocationManager locationManager = (LocationManager)
                    getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));

            //Toast.makeText(getActivity(), "Permisos   otorgados", Toast.LENGTH_LONG).show();

            return true;
        }
        return false;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            } else {
                Toast.makeText(getActivity(), "Permisos no otorgados", Toast.LENGTH_LONG).show();
            }
        }
    }


    public LatLng ubicacion() {

        //Instancia de la clase locationmanager para obtener la unicacion
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;// Seleccion del provehedor de unicacion

        //Comprobacion de los permisos de  ubicacion
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation != null) {
            //Toast.makeText(getContext(), "Ultima conocida   " + lastKnownLocation.getLongitude(), Toast.LENGTH_SHORT).show();
            aqui = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            return aqui;

        }//Tomo la  ultima  ubicacion conocida y la retorno

        //Listener que escucha si hubo algun cambio en la unicacion y  si el provehedor de ubicacion esta  activo
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                aqui = new LatLng(location.getLatitude(), location.getLongitude());

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        }
        locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
        return aqui;
    }


    public void Guardar_item_Ruta(Marker marker) {

        if (marker != null) {
            String[] info = marker.getTitle().split("&");
            Toast.makeText(getActivity(), "" +info[0], Toast.LENGTH_SHORT).show();
                bd_express.Guardar_Items_ruta(info[2], info[0],info[4]);

        }
    }



}
