package com.expres_cadetes_espartadev.expres_cadetes.Modelos;

public class Mo_Items_servicio_cliente {
    String Servicio,Actividad;

    public Mo_Items_servicio_cliente() {
    }


    public Mo_Items_servicio_cliente(String servicio, String actividad) {
        Servicio = servicio;
        Actividad = actividad;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String servicio) {
        Servicio = servicio;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }
}
