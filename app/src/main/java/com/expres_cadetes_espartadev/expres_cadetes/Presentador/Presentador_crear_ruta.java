package com.expres_cadetes_espartadev.expres_cadetes.Presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;
import com.expres_cadetes_espartadev.expres_cadetes.Volley_singleton.Volley_singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Presentador_crear_ruta {
    Context context;
    private BD_Express bd_express;//Instancia de la base de datos
    private RequestQueue quere;

    public Presentador_crear_ruta(Context context) {
        this.context = context;
    }

    public void Ruta_terminada(final Map<String, String> datos) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = "http://espartadevs.website/express/public/api/workloads";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                String estado = "";
                String placa = "";
                String identificacion = "";
                String token = "";
                JSONObject res = null;
                final JSONObject PRIMER;
                JSONArray jArray = null;
                //t.setText(response);
                if (response != null) {
                    Toast.makeText(context, "respuesta " + response, Toast.LENGTH_SHORT).show();
                    try {
                        res = new JSONObject(response.toString());
                        if (res.optString("response").equals("true")) {
                            /*
                            PRIMER = new JSONObject(res.optString("result"));
                            jArray = PRIMER.getJSONArray("data"); //Obtenemos el array results
                            token=PRIMER.optString("token"); //Obtenemos el array results
                            //Toast.makeText(context, " eeeeeeeeeeeee"+ sharedPreferences.getString("token",""), Toast.LENGTH_SHORT).show();
                            String response1 = res.optString("response");
                            String mensajes = res.optString("message");
                            //  Toast.makeText(context, "esto " + response1 + "  " + mensajes, Toast.LENGTH_SHORT).show();
*/



                        } else {

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        Log.e("ErrorVolley: ",e.toString());
                    }


                }


                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorVolley: ",error.toString());
                Toast.makeText(context, "Ha ocurrido un error  en el Login", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return datos;

            }


        };

        Volley_singleton.getInstancia_volley(context).addToRequestQueue(request);
    }
}
