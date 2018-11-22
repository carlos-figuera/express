package com.expres_cadetes_espartadev.expres_cadetes.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_servicio_cliente;
import com.expres_cadetes_espartadev.expres_cadetes.R;

import java.util.List;

public class Adaptador_Item_servicio_cliente extends RecyclerView.Adapter<Adaptador_Item_servicio_cliente.ViewHolder> implements View.OnClickListener {

    public List<Mo_Items_servicio_cliente> Lista_servico;
    private View.OnClickListener listener;
    private Context c;

    public Adaptador_Item_servicio_cliente(List<Mo_Items_servicio_cliente> lista_servico) {
        Lista_servico = lista_servico;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_items_servicio_cliente, parent, false);
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.Txt_servicio_cli.setText(Lista_servico.get(position).getServicio());
        holder.Txt_Actividad_cli.setText(Lista_servico.get(position).getActividad());


    }

    @Override
    public int getItemCount() {
        return Lista_servico.size();
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Txt_servicio_cli, Txt_Actividad_cli;
        private LinearLayout L_item_rutao;
        private ImageView Img_Foto_perfil_cli,Img_Estado,Img_tiempo,Img_borrar;

        public ViewHolder(View itemView) {
            super(itemView);

            Txt_servicio_cli = (TextView) itemView.findViewById(R.id.Txt_servicio_cli);
            Txt_Actividad_cli = (TextView) itemView.findViewById(R.id.Txt_Actividad_cli);
            Img_borrar = (ImageView) itemView.findViewById(R.id.Img_borrar);


        }
    }


}
