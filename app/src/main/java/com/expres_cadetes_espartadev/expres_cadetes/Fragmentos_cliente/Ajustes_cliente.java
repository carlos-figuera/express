package com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cliente;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.expres_cadetes_espartadev.expres_cadetes.R;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ajustes_cliente extends Fragment implements View.OnClickListener {
View view;
ImageView Img_Foto_perfil_cliente;    public Ajustes_cliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_ajustes_cliente, container, false);





        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }



    public void cargar_imagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Selecciona una imagen"),10 );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (resultCode==RESULT_OK){
        Uri paht= data.getData();
        Img_Foto_perfil_cliente.setImageURI(paht);
    }

}
}