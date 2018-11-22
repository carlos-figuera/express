package com.expres_cadetes_espartadev.expres_cadetes.Modelos;

public class Mo_Items_ruta {
    String ID, Nombre_cli,Direccion_cli;

    public Mo_Items_ruta() {
    }

    public Mo_Items_ruta(String ID, String nombre_cli, String direccion_cli) {
        this.ID = ID;
        Nombre_cli = nombre_cli;
        Direccion_cli = direccion_cli;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre_cli() {
        return Nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        Nombre_cli = nombre_cli;
    }

    public String getDireccion_cli() {
        return Direccion_cli;
    }

    public void setDireccion_cli(String direccion_cli) {
        Direccion_cli = direccion_cli;
    }
}
