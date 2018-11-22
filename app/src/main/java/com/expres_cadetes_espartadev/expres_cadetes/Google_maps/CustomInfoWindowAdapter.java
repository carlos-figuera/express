package com.expres_cadetes_espartadev.expres_cadetes.Google_maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter  implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;
    Context context;

    public CustomInfoWindowAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public View getInfoWindow(final Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents( final Marker marker) {

        View v = inflater.inflate(R.layout.car_marcador, null);
        String[] info = marker.getTitle().split("&");
        String url = marker.getSnippet();
        ((TextView)v.findViewById(R.id.Txt_nombre_cli)).setText( info[0]);
        ((TextView)v.findViewById(R.id.Txt_empresa_ser)).setText( info[1]);
        ((TextView)v.findViewById(R.id.Txt_identificacion_ser)).setText(info[2]);
        ((TextView)v.findViewById(R.id.Txt_estado_ser)).setText(info[3] );

        ((TextView)v.findViewById(R.id.Txt_direccion_ser)).setText( marker.getSnippet().toString() );


        return v;
    }
}
