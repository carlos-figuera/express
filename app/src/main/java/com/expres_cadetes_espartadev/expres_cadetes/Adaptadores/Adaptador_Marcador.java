package com.expres_cadetes_espartadev.expres_cadetes.Adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.expres_cadetes_espartadev.expres_cadetes.SQLite.BD_Express;

import java.util.List;

public class Adaptador_Marcador extends RecyclerView.Adapter<Adaptador_Marcador.ViewHolder> implements View.OnClickListener {

    public List<Mo_Items_ruta> Lista_ruta;
    private View.OnClickListener listener;
    private Context context;
    Dialog dialog;
    RecyclerView recyclerView;


    private BD_Express bd_express;//Instancia de la base de datos

    public Adaptador_Marcador(List<Mo_Items_ruta> lista_ruta, RecyclerView recyclerView) {
        Lista_ruta = lista_ruta;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_items_ruta, parent, false);
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.Txt_Nombre_cli.setText(Lista_ruta.get(position).getNombre_cli());
        holder.Txt_direccion_cli.setText(Lista_ruta.get(position).getDireccion_cli());
        holder.Img_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar_servicio(holder.Txt_Nombre_cli.getText().toString(), holder.Txt_direccion_cli.getText().toString());
            }
        });

        holder.Img_tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Poponer_servicio(holder.Txt_Nombre_cli.getText().toString(), holder.Txt_direccion_cli.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return Lista_ruta.size();
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Txt_Nombre_cli, Txt_direccion_cli;
        private LinearLayout L_item_rutao;
        private ImageView Img_Foto_perfil_cli, Img_Estado, Img_tiempo, Img_borrar;

        public ViewHolder(View itemView) {
            super(itemView);

            Txt_Nombre_cli = (TextView) itemView.findViewById(R.id.Txt_Nombre_cli);
            Txt_direccion_cli = (TextView) itemView.findViewById(R.id.Txt_direccion_cli);



            Img_Estado = (ImageView) itemView.findViewById(R.id.Img_Estado);
            Img_tiempo = (ImageView) itemView.findViewById(R.id.Img_tiempo);
            Img_borrar = (ImageView) itemView.findViewById(R.id.Img_borrar);


        }
    }


    public void Eliminar_servicio(String nombre, String direccion) {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_servicio);
        final TextView nombre_cli = (TextView) dialog.findViewById(R.id.Txt_nombre_cli);


        ImageView Img_Cancelar = (ImageView) dialog.findViewById(R.id.Img_Cancelar);
        Img_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        ImageView Img_add_service = (ImageView) dialog.findViewById(R.id.Img_add_service);
        Img_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd_express.borrar_Item_ruta(nombre_cli.getText().toString());
                Toast.makeText(context, "Eliminaddo "+ nombre_cli.getText().toString(), Toast.LENGTH_SHORT).show();

                Acualizar();
                dialog.dismiss();
            }
        });
        nombre_cli.setText(nombre);

        dialog.show();

    }

    public void Poponer_servicio(String nombre, String direccion) {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_servicio);
        final TextView nombre_cli = (TextView) dialog.findViewById(R.id.Txt_nombre_cli);


        ImageView Img_Cancelar = (ImageView) dialog.findViewById(R.id.Img_Cancelar);
        Img_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        ImageView Img_add_service = (ImageView) dialog.findViewById(R.id.Img_add_service);
        Img_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Pospuesto "+ nombre_cli.getText().toString(), Toast.LENGTH_SHORT).show();

                Acualizar();
                dialog.dismiss();
            }
        });
        nombre_cli.setText(nombre);

        dialog.show();

    }



    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
        switch (view.getId()) {
            case R.id.Txt_nombre_cli:
                dialog.dismiss();
                break;

        }

    }

    public void Acualizar(){
        Adaptador_Marcador adaptador_item_ruta ;
        adaptador_item_ruta = new Adaptador_Marcador(bd_express.get_Items_de_ruta(),recyclerView);
        recyclerView.setAdapter(adaptador_item_ruta);


    }


}
