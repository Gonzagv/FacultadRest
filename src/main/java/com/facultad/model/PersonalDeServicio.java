package com.facultad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDeServicio extends Empleado {
    private String seccion;

    public PersonalDeServicio(){}

    public PersonalDeServicio(String nombre, String apellido,String dni, CargoEnum cargo, String anioDeIncorpora, Double salario, String seccion) {
        super(nombre, apellido, dni, cargo, anioDeIncorpora, salario);
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
