package com.example.lab2_grupo2.entidades;

import com.google.gson.annotations.SerializedName;

public class ApiKey {

    private String estado;
    private int cuota;
    @SerializedName("api-key")
    private String apiKey;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
