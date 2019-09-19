package com.facultad.models.empleado;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Administrativo extends Empleado{

    private String sector;

    public Administrativo(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, Double salario, String sector) {
        super(nombre, apellido, dni, cargo, anioDeIncorpora, salario);
        this.sector = sector;
    }

    public Administrativo(){}

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

}
