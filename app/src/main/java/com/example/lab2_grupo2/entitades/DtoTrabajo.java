package com.example.lab2_grupo2.entitades;

public class DtoTrabajo {
    private String estado;
    private Trabajo[] trabajos;
private int cuota;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Trabajo[] getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(Trabajo[] trabajos) {
        this.trabajos = trabajos;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }
}
