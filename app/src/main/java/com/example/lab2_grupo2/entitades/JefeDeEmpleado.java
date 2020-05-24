package com.example.lab2_grupo2.entitades;

import com.android.volley.toolbox.StringRequest;

public class JefeDeEmpleado {

    private String emplyoeeId;
    private String firstName;
    private String lastName;
    private String email;

    public String getEmplyoeeId() {
        return emplyoeeId;
    }

    public void setEmplyoeeId(String emplyoeeId) {
        this.emplyoeeId = emplyoeeId;
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
}

