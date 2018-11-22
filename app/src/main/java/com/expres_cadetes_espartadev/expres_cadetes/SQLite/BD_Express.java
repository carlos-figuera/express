package com.expres_cadetes_espartadev.expres_cadetes.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Items_ruta;
import com.expres_cadetes_espartadev.expres_cadetes.Modelos.Mo_Todos_los_Servicios;

import java.util.ArrayList;
import java.util.List;

public class BD_Express extends SQLiteOpenHelper {
    private static String Todos_Servicios = "CREATE TABLE Todos_Servicio(id TEXT,ent_id TEXT,name TEXT,serie TEXT,state TEXT,location TEXT,address TEXT ,cp TEX,status TEX,client_id TEX,x TEX,y TEX)";
    private static String Ruta = "CREATE TABLE Ruta_service(ID TEXT,NAME TEXT,DIRECCION TEXT)";

    private static String TempServicio = "CREATE TABLE Temp_Servicio(ID TEXT,NAME TEXT,DESCRIPCION TEXT,ESTADO TEXT,CLIENTE_ID TEXT,lONGITUDE TEXT,LATITUDE TEXT)";




    public BD_Express(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Todos_Servicios);
        db.execSQL(TempServicio);
        db.execSQL(Ruta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //  Metodo para  guardar todos los servicios que vienen de la api
    public void Guardar_Temp__servicios(String ID, String NAME, String DESCRIPCION, String ESTADO, String CLIENTE_ID, String lONGITUDE, String LATITUDE) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Temp_Servicio VALUES('" + ID + "','" + NAME + "','" + DESCRIPCION + "','" + ESTADO + "','" + CLIENTE_ID + "','" + lONGITUDE + "','" + LATITUDE + "')");
            db.close();
        }
    }

    //  Metodo para crear una  lista con todos los servicios que viene  de la  api
    public List<Mo_Todos_los_Servicios> get_Temp_servicios() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Temp_Servicio", null);
        List<Mo_Todos_los_Servicios> LIST = new ArrayList<Mo_Todos_los_Servicios>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Mo_Todos_los_Servicios(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7) , cursor.getString(8), cursor.getString(9) , cursor.getString(10), cursor.getString(11) ));

                cursor.moveToNext();
            }

        }

        return LIST;
    }


    //  Metodo para  guardar todos los servicios que vienen de la api
    public void Guardar_Todos_servicios(String id, String ent_id, String name, String serie, String state, String location, String address, String cp, String status, String client_id, String x, String y) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Todos_Servicio VALUES('" + id + "','" + ent_id + "','" + name + "','" + serie + "','" + state + "','" + location + "','" + address + "','"+cp+"','"+status+"','"+client_id+"','"+x+"','"+y+"')");
            db.close();
        }
    }




    //  Metodo para crear una  lista con todos los servicios que viene  de la  api
    public List<Mo_Todos_los_Servicios> get_Todos_servicios() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Todos_Servicio", null);
        List<Mo_Todos_los_Servicios> LIST = new ArrayList<Mo_Todos_los_Servicios>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Mo_Todos_los_Servicios(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7) , cursor.getString(8), cursor.getString(9) , cursor.getString(10), cursor.getString(11) ));

                cursor.moveToNext();
            }

        }

        return LIST;
    }



    //Metodo para   obtener los detalles de  un servicio  añadido a la  ruta
    public List<Mo_Todos_los_Servicios> get_servicio(String ID_SERVICE) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Todos_Servicio WHERE ID='" + ID_SERVICE + "'", null);
        List<Mo_Todos_los_Servicios> LIST = new ArrayList<Mo_Todos_los_Servicios>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {

                //LIST.add(new Mo_Todos_los_Servicios (cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener los detalles de un pago





    //  Metodo para  guardar todos los servicios que vienen de la api
    public void Guardar_Items_ruta(String ID, String NAME, String DIRECCION) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Ruta_service VALUES('" + ID + "','" + NAME + "','" + DIRECCION + "'  )");
            db.close();
        }
    }

    //Metodo para   obtener todos los servicios que fueros seleccionados para la ruta
    public List<Mo_Items_ruta> get_Items_de_ruta() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Ruta_service", null);
        List<Mo_Items_ruta> LIST = new ArrayList<Mo_Items_ruta>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Mo_Items_ruta(cursor.getString(0), cursor.getString(1), cursor.getString(2)));

                cursor.moveToNext();
            }

        }

        return LIST;
    }

    //Metodo para   obtener los detalles de un SERVICIO
    public List<Mo_Todos_los_Servicios> get_detalles_Item_ruta(String ID_SERVICE) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Ruta_service WHERE ID='" + ID_SERVICE + "'", null);
        List<Mo_Todos_los_Servicios> LIST = new ArrayList<Mo_Todos_los_Servicios>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {

                //LIST.add(new Mo_Todos_los_Servicios (cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener los detalles de un pago

    //  metodo para  borrar alguna tabla
    public void borrar_Item_ruta(String ID) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM Ruta_service  WHERE ID='"+ID+"'");
            bd.close();
        }


    }






    public void Actualizar_ESTADO_SERVICIO(String recordar, String Usuario) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            if (Usuario != null) {
                bd.execSQL("UPDATE Login SET RECORDAR='" + recordar + "' WHERE USUARIO='" + Usuario + "'");
                bd.close();
            }

        }
    }// METODO PARA CAMBIAR EL ESTADO DE PERSISTENCIA DE LA CONTRASEÑA




    //  comprueba si una tabla esta vacia
    public boolean carga_inicial(String TABLA) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM '" + TABLA + "'", null);
        if (cursor.getCount() < 1) {
            return false;
        }
        return true;
    }



    //  metodo para  borrar alguna tabla
    public void borrar_TEMP(String TABLA) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM '" + TABLA + "'");
            bd.close();
        }


    }


    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        } else {

            return false;
        }
    }//  Metodo para verificar la conexion a internet







    /*
    public List<Detalles_pago> get_Detalles_Pago(String COD_PAGO) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Detalles_pago WHERE COD_PAGO='" + COD_PAGO + "'", null);
        List<Detalles_pago> LIST = new ArrayList<Detalles_pago>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Detalles_pago(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20)));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener los detalles de un pago


    //  Metodo para  guardar LOS PRESTAMOS DE UN CLIENTE
    public void Guardar_prestamos(String COD_CLIENTE, String COD_PRESTAMO, String PUNTOS, String PORCENTAJE, String TASA, String ESTADO, String PROX_PAGO, String CAPITAL_PRESTAMO, String INTERES_PRESTAMO, String MORA_PRESTAMO, String CAPITAL_DEUDA, String INTERES_DEUDA, String MORA_DEUDA, String TOTAL_CAPITAL, String TOTAL_deuda, String DIAS, String DIAS_REFERENCIA, String FECHA_FIN) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO ListaPrestamos VALUES('" + COD_CLIENTE + "','" + COD_PRESTAMO + "','" + PUNTOS + "','" + PORCENTAJE + "','" + TASA + "','" + ESTADO + "','" + PROX_PAGO + "','" + CAPITAL_PRESTAMO + "','" + INTERES_PRESTAMO + "','" + MORA_PRESTAMO + "','" + CAPITAL_DEUDA + "','" + INTERES_DEUDA + "','" + MORA_DEUDA + "','" + TOTAL_CAPITAL + "','" + TOTAL_deuda + "','" + DIAS + "','" + DIAS_REFERENCIA + "','" + FECHA_FIN + "' )");
            db.close();
        }
    }


    public List<Lista_prestamos> getPrestamos_CLIENTE(String COD_CLIENTE) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  ListaPrestamos WHERE COD_CLIENTE='" + COD_CLIENTE + "'", null);
        List<Lista_prestamos> LIST = new ArrayList<Lista_prestamos>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y luego lleno la lista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Lista_prestamos(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17)));

                cursor.moveToNext();
            }

        }

        return LIST;
    } //Metodo para   obtener los prestamos  de un cliente


    //  Metodo para  guardar Los Pagos DE UN PRESTAMO
    public void Guardar_pagos_prestamo(String COD_PAGO, String DESCRIPCION, String MONTO, String ESTADO, String FECHA_PAGO, String COD_PRESTAMOS, String COD_AMORTIZACION) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Historial_Pagos VALUES('" + COD_PAGO + "','" + DESCRIPCION + "','" + MONTO + "','" + ESTADO + "','" + FECHA_PAGO + "','" + COD_PRESTAMOS + "','" + COD_AMORTIZACION + "' )");
            db.close();
        }
    }


    public List<Iten_pago> getPago_Prestamos(String COD_PRESTAMO) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Historial_Pagos WHERE COD_PRESTAMO='" + COD_PRESTAMO + "'", null);
        List<Iten_pago> LIST = new ArrayList<Iten_pago>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {


                LIST.add(new Iten_pago(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener el historial de pagos  de un   prestamo


    //Metodo para  guardar los mensajes que vienen de la api

    public void Guardar_mensajes(String telefono, String mensajes, String telefono_format) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO EnvioMensajes VALUES('" + telefono + "','" + mensajes + "','" + telefono_format + "')");
            db.close();
        }
    }

    public List<mensajes_1> getMensajes() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM EnvioMensajes", null);
        List<mensajes_1> LIST = new ArrayList<mensajes_1>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {
                String numero = cursor.getString(0);
                String mensaje = cursor.getString(1);
                String numero_format = cursor.getString(2);

                LIST.add(new mensajes_1(numero, mensaje, numero_format));

                cursor.moveToNext();
            }

        }

        return LIST;
    }////Metodo para   obtener los mensajes que vienen de la api


    //  Metodo para  guardar  los mensajes enviados
    public void Guardar_mensajes_Enviados(String telefono, String mensajes, String telefono_format) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO TempEnviados VALUES('" + telefono + "','" + mensajes + "','" + telefono_format + "')");
            db.close();
        }
    }

    public List<mensajes_1> getMensajes_Enviados() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM TempEnviados", null);
        List<mensajes_1> LIST = new ArrayList<mensajes_1>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {
                String numero = cursor.getString(0);
                String mensaje = cursor.getString(1);
                String numero_format = cursor.getString(2);

                LIST.add(new mensajes_1(numero, mensaje, numero_format));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener los mensajes  enviados


    //  Metodo para  guardar temporalmente los mensajes  con error
    public void Guardar_mensajes_Error(String telefono, String mensajes, String numero_format) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO TempErrores VALUES('" + telefono + "','" + mensajes + "','" + numero_format + "')");
            db.close();
        }
    }

    public List<mensajes_1> getMensajes_Enviados_con_error() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM TempErrores", null);
        List<mensajes_1> LIST = new ArrayList<mensajes_1>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {
                String numero = cursor.getString(0);
                String mensaje = cursor.getString(1);
                String numero_format = cursor.getString(2);

                LIST.add(new mensajes_1(numero, mensaje, numero_format));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//Metodo para   obtener los mensajes  enviados con error


    //  Guardar datos de la compañia

    public void Mostrar_datos_compañia(Datos_compania datos_compania) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM Compania", null);
        // Cursor cursor=bd.rawQuery("SELECT *FROM Cargar_Apis WHERE DNI='"+dni+"'",null);
        if (cursor.moveToFirst()) {
            do {
                datos_compania.setNombre(cursor.getString(0).toString());
                datos_compania.setTelefono(cursor.getString(1).toString());
                datos_compania.setRcr(cursor.getString(2).toString());
                datos_compania.setCorreo(cursor.getString(3).toString());
                datos_compania.setEsloga(cursor.getString(4).toString());
                datos_compania.setDireccion(cursor.getString(5).toString());

            } while (cursor.moveToNext());
        }

    }    // metodos para mostar los datos  de la compalia en pantalla

    public void Guardar_compania(String NOMBRE, String TELEFONO, String RCR, String CORREO, String ESLOGAN, String DIRECCION) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Compania VALUES('" + NOMBRE + "','" + TELEFONO + "','" + RCR + "','" + CORREO + "','" + ESLOGAN + "','" + DIRECCION + "' )");
            db.close();
        }
    } //Metodo para   obtener los datos de una compañia


    //  comprueba si una tabla esta vacia
    public boolean carga_inicial(String TABLA) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM '" + TABLA + "'", null);
        // Cursor cursor=bd.rawQuery("SELECT *FROM TEMP_PARTE1 WHERE DNI='"+dni+"'",null);
        if (cursor.getCount() < 1) {
            return false;
        }
        return true;
    }


    public void Login(String USUARIO, String CLAVE, String RECORDAR, String COD_CLIENTE, String COMPANIA, String TIPO_USUARIO, String ENVIASMS) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO  Login VALUES('" + USUARIO + "','" + CLAVE + "','" + RECORDAR + "','" + COD_CLIENTE + "','" + COMPANIA + "','" + TIPO_USUARIO + "','" + ENVIASMS + "' )");
            db.close();
        }
    }

    public void logearse(String usuario, Usuario usuario_m) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM  Login WHERE USUARIO='" + usuario + "'", null);

        try {

            if (cursor.moveToFirst()) {

                //iteramos  sobre el cursor y luego lleno la lista


                do {
                    usuario_m.setUsuario(cursor.getString(0));
                    usuario_m.setClave(cursor.getString(1));
                    usuario_m.setRecordar(cursor.getString(2));
                    usuario_m.setCoo_cliente(cursor.getString(3));
                    usuario_m.setCompania(cursor.getString(4));
                    usuario_m.setTipo_usuario(cursor.getString(5));
                    usuario_m.setEnviasms(cursor.getString(6));

                } while (cursor.moveToNext());


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void Cargar_modelo_usuario(Usuario usuario_m) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM Login", null);
        // Cursor cursor=bd.rawQuery("SELECT *FROM Cargar_Apis WHERE DNI='"+dni+"'",null);
        try {

            if (cursor.moveToFirst()) {

                //iteramos  sobre el cursor y luego lleno la lista


                do {
                    usuario_m.setUsuario(cursor.getString(0));
                    usuario_m.setClave(cursor.getString(1));
                    usuario_m.setRecordar(cursor.getString(2));
                    usuario_m.setCoo_cliente(cursor.getString(3));
                    usuario_m.setCompania(cursor.getString(4));
                    usuario_m.setTipo_usuario(cursor.getString(5));
                    usuario_m.setEnviasms(cursor.getString(6));

                } while (cursor.moveToNext());


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //  metodos  para  configurar las apis

    public void Guardar_apis(String CODIGO, String DESCRIPCION, String ABREVIATURA, String VALOR) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO Apis VALUES('" + CODIGO + "','" + DESCRIPCION + "','" + ABREVIATURA + "','" + VALOR + "')");
            db.close();
        }
    }

    public String getApis1(String ABREVIATURA) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM Apis WHERE ABREVIATURA ='" + ABREVIATURA + "'", null);
        List<Apis> LIST = new ArrayList<Apis>();
        String url = "";
        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {

                url = cursor.getString(3);


                cursor.moveToNext();
            }

        }

        return url;
    }//METODO PARA OBTENER  UNA API SEGUN CRITERIO DE BUSQUEDA

    public List<Apis> getApis() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM Apis", null);
        List<Apis> LIST = new ArrayList<Apis>();

        if (cursor.moveToFirst()) {

            //iteramos  sobre el cursor y lugo lleno lalista

            while (cursor.isAfterLast() == false) {
                String cogido = cursor.getString(0);
                String abreviatura = cursor.getString(1);
                String descripcion = cursor.getString(2);
                String valor = cursor.getString(3);

                LIST.add(new Apis(cogido, abreviatura, descripcion, valor));

                cursor.moveToNext();
            }

        }

        return LIST;
    }//METODO PARA OBTENER  UNA LISTA  DE LAS APIS GUARDADAS





*/
}