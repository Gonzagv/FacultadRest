package com.Facultad_Rest.model;

public class Administrativo extends Empleado{

    private String seccion;

    public Administrativo(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, int salario, String seccion) {
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
