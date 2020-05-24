package com.example.lab2_grupo2.entidades;

import java.io.Serializable;

public class Departamento implements Serializable {
    private int departmentId;
    private String departmentName;
    private String managerId;
    private int locationId;
    private String departmentShortName;


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDepartmentShortName() {
        return departmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        //Solo guardo en caso que departmentShortName contenga 2 caracteres
        if(departmentShortName.length() ==2){
            this.departmentShortName = departmentShortName;
        }
    }
}
