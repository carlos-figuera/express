package com.expres_cadetes_espartadev.expres_cadetes.Modelos;

public class Mo_Todos_los_Servicios {


    String id,ent_id,  name,  serie,  state,  location,  address, cp,  status, client_id, x, y;


    public Mo_Todos_los_Servicios() {
    }


    public Mo_Todos_los_Servicios(String id, String ent_id, String name, String serie, String state, String location, String address, String cp, String status, String client_id, String x, String y) {
        this.id = id;
        this.ent_id = ent_id;
        this.name = name;
        this.serie = serie;
        this.state = state;
        this.location = location;
        this.address = address;
        this.cp = cp;
        this.status = status;
        this.client_id = client_id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnt_id() {
        return ent_id;
    }

    public void setEnt_id(String ent_id) {
        this.ent_id = ent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
