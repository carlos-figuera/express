package com.expres_cadetes_espartadev.expres_cadetes.Modelos;

import java.util.List;

public class Mo_Json_ruta {
    String cadete_id;
    List<Mo_id_ruta> id;

    public Mo_Json_ruta(String cadete_id, List<Mo_id_ruta> id) {
        this.cadete_id = cadete_id;
        this.id = id;
    }

    public String getCadete_id() {
        return cadete_id;
    }

    public void setCadete_id(String cadete_id) {
        this.cadete_id = cadete_id;
    }

    public List<Mo_id_ruta> getId() {
        return id;
    }

    public void setId(List<Mo_id_ruta> id) {
        this.id = id;
    }
}
