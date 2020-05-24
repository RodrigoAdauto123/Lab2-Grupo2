package com.example.lab2_grupo2.Entidades2;

import java.io.Serializable;

public class EmpleadoDTO implements Serializable {

    private String estado;
    private int cuota;
    private Empleado[] lista;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public Empleado[] getLista() {
        return lista;
    }

    public void setLista(Empleado[] lista) {
        this.lista = lista;
    }
}
