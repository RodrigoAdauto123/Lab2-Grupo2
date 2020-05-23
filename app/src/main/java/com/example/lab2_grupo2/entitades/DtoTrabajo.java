package com.example.lab2_grupo2.entitades;

import java.io.Serializable;

public class DtoTrabajo implements Serializable {
    private String estado;
    private TrabajoListar[] trabajoListars;
private int cuota;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TrabajoListar[] getTrabajoListars() {
        return trabajoListars;
    }

    public void setTrabajoListars(TrabajoListar[] trabajoListars) {
        this.trabajoListars = trabajoListars;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }
}
