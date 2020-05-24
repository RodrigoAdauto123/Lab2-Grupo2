package com.example.lab2_grupo2.entitades;

import com.example.lab2_grupo2.entidades.Departamento;

import java.io.Serializable;

public class Empleado implements Serializable {

    private String emplyoyeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
            private Trabajo jobId;
private double salary;
private double comissionPct;
private Departamento departmentId;
private JefeDeEmpleado managerId;
private String createdBy;


    public String getEmplyoyeeId() {
        return emplyoyeeId;
    }

    public void setEmplyoyeeId(String emplyoyeeId) {
        this.emplyoyeeId = emplyoyeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Trabajo getJobId() {
        return jobId;
    }

    public void setJobId(Trabajo jobId) {
        this.jobId = jobId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getComissionPct() {
        return comissionPct;
    }

    public void setComissionPct(double comissionPct) {
        this.comissionPct = comissionPct;
    }

    public Departamento getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Departamento departmentId) {
        this.departmentId = departmentId;
    }

    public JefeDeEmpleado getManagerId() {
        return managerId;
    }

    public void setManagerId(JefeDeEmpleado managerId) {
        this.managerId = managerId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
