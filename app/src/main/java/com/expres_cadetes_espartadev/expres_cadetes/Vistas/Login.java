package com.expres_cadetes_espartadev.expres_cadetes.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.expres_cadetes_espartadev.expres_cadetes.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
 private Button Btn_ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Btn_ingresar=(Button)findViewById(R.id.Btn_ingresar);
        Btn_ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btn_ingresar:
                Intent i = new Intent(this,Principal.class);
                startActivity(i);
                break;

        }
    }
}
