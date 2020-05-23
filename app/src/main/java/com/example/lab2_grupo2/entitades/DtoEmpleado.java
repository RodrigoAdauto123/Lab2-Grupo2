package com.example.lab2_grupo2.entitades;

import java.io.Serializable;

public class DtoEmpleado implements Serializable {

    private String estado;
    private Empleado[] lista;
    private String cuota;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado[] getLista() {
        return lista;
    }

    public void setLista(Empleado[] lista) {
        this.lista = lista;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }
}
