package com.example.lab2_grupo2.entidades;

public class DepartamentoDTO {
    private String estado;
    private int cuota;
    private Departamento[] lista;

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


    public Departamento[] getLista() {
        return lista;
    }

    public void setLista(Departamento[] lista) {
        this.lista = lista;
    }
}
