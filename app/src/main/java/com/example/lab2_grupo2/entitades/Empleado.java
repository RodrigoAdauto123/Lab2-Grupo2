package com.example.lab2_grupo2.entitades;

import com.example.lab2_grupo2.entidades.Departamento;
import com.example.lab2_grupo2.entidades.Trabajo;

public class Empleado {

    private int employee_id;
    private String first_name;
    private String last_name;
    private String correoElectronico;
    private int salario;
    private int comision;
    private String numeroCelular;
    // private Empleado empleado;
    // private Trabajo trabajo;
    // private Departamento departamento;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroCelular() {
        return numeroCelular;
    }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }

    public int getSalario() {
        return salario;
    }
    public void setSalario(int salario) { this.salario = salario; }

    public int getsComision() {
        return comision;
    }
    public void setComision(int comision) { this.comision = comision; }





}
