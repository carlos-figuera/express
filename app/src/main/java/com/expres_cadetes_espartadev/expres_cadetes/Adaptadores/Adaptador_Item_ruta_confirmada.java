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

public class Adaptador_Item_ruta_confirmada extends RecyclerView.Adapter<Adaptador_Item_ruta_confirmada.ViewHolder> implements View.OnClickListener {

    public List<Mo_Items_ruta> Lista_ruta;
    private View.OnClickListener listener;
    private Context context;
    Dialog dialog;
    RecyclerView recyclerView;


    private BD_Express bd_express;//Instancia de la base de datos

    public Adaptador_Item_ruta_confirmada(List<Mo_Items_ruta> lista_ruta, RecyclerView recyclerView) {
        Lista_ruta = lista_ruta;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_items_ruta_confirmada, parent, false);
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.Txt_Nombre_cli.setText(Lista_ruta.get(position).getNombre_cli());
        holder.Txt_direccion_cli.setText(Lista_ruta.get(position).getDireccion_cli());

        holder.Txt_id_cli.setText(Lista_ruta.get(position).getID()+" ");

        holder.Img_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar_servicio(holder.Txt_id_cli.getText().toString().trim());
            }
        });



        holder.Img_confirmado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.Img_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VER_DETALES();
            }
        });
        holder.Img_pospner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Poponer_servicio();
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
        private TextView Txt_Nombre_cli, Txt_direccion_cli,Txt_id_cli;
        private LinearLayout L_item_rutao;
        private ImageView Img_confirmado, Img_ver, Img_pospner, Img_borrar;

        public ViewHolder(View itemView) {
            super(itemView);
            Txt_id_cli = (TextView) itemView.findViewById(R.id.Txt_id_cli);
            Txt_Nombre_cli = (TextView) itemView.findViewById(R.id.Txt_Nombre_cli);
            Txt_direccion_cli = (TextView) itemView.findViewById(R.id.Txt_direccion_cli);
            Img_confirmado = (ImageView) itemView.findViewById(R.id.Img_confirmado);
            Img_ver = (ImageView) itemView.findViewById(R.id.Img_ver);
            Img_pospner = (ImageView) itemView.findViewById(R.id.Img_pospner);
            Img_borrar = (ImageView) itemView.findViewById(R.id.Img_borrar);


        }
    }


    public void Eliminar_servicio(final String nombre) {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        dialog = new Dialog(context);
        dialog.setTitle("Eliminar marcador");
        dialog.setContentView(R.layout.dialog_servicio);

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
                bd_express.borrar_Item_ruta(nombre);
                Toast.makeText(context, "Eliminaddo  " + nombre, Toast.LENGTH_SHORT).show();

                Acualizar();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void Poponer_servicio() {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        dialog = new Dialog(context);
        dialog.setTitle("Posponer servicio");
        dialog.setContentView(R.layout.dialog_servicio);

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

                Toast.makeText(context, "Pospuesto "  , Toast.LENGTH_SHORT).show();

                Acualizar();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void VER_DETALES() {
        bd_express = new BD_Express(context, "BD_EXPRESS", null, 1);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_ver);
        dialog.setTitle("Detalles del servicio");
        TextView Txt_nombre_cli = (TextView) dialog.findViewById(R.id.Txt_nombre_cli);
        TextView Txt_empresa_ser = (TextView) dialog.findViewById(R.id.Txt_empresa_ser);
        TextView Txt_identificacion_ser = (TextView) dialog.findViewById(R.id.Txt_identificacion_ser);
        TextView Txt_estado_ser = (TextView) dialog.findViewById(R.id.Txt_estado_ser);
        TextView Txt_direccion_ser = (TextView) dialog.findViewById(R.id.Txt_direccion_ser);

        Txt_nombre_cli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });




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
        Adaptador_Item_ruta_confirmada adaptador_item_ruta ;
        adaptador_item_ruta = new Adaptador_Item_ruta_confirmada(bd_express.get_Items_de_ruta(),recyclerView);
        recyclerView.setAdapter(adaptador_item_ruta);

    }


}
