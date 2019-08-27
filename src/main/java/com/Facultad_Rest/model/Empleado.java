package com.Facultad_Rest.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Empleado {
    private String nombre;
    private String apellido;
    @NotNull
    @NotEmpty
    private String dni;
    private CargoEnum cargo;
    private String anioDeIncorpora;
    private int salario;

    public Empleado(){}

    public Empleado(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, int salario) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
        this.anioDeIncorpora = anioDeIncorpora;
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getNombre() { return nombre; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public String getAnioDeIncorpora() {
        return anioDeIncorpora;
    }

    public void setAnioDeIncorpora(String anioDeIncorpora) {
        this.anioDeIncorpora = anioDeIncorpora;
    }

}