package com.expres_cadetes_espartadev.expres_cadetes.Vistas;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas.Crear_Ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas.Ruta_confirmada;
import com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cliente.Ajustes_cliente;
import com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cliente.Servicios_cliente;
import com.expres_cadetes_espartadev.expres_cadetes.R;
import com.expres_cadetes_espartadev.expres_cadetes.Fragmentos_cadetes.Rutas.container_crear_ruta;

import java.sql.Blob;


public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    Blob d;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor_fragmets, new container_crear_ruta()).commit();


    }



    @Override
    protected void onStart() {
        super.onStart();
        toolbar.setTitle("Express");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Notificaciones) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Crear_Ruta) {
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor_fragmets, new container_crear_ruta()).commit();

        } else if (id == R.id.Ver_Rutas) {


            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor_fragmets, new Ruta_confirmada()).commit();

        } else if (id == R.id.Mis_servicios) {
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor_fragmets, new Servicios_cliente()).commit();

        } else if (id == R.id.Ajuste_cliente) {
            toolbar.setTitle("Ajustes");
            fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor_fragmets, new Ajustes_cliente()).commit();

        } else if (id == R.id.Salir) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
