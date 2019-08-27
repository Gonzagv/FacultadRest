package com.facultad.model;

public class Administrativo extends Empleado{

    private String sector;

    public Administrativo(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, int salario, String sector) {
        super(nombre, apellido, dni, cargo, anioDeIncorpora, salario);
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

}
