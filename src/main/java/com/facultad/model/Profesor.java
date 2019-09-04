package com.facultad.model;

public class Profesor extends Empleado {
    private String materia;
    private String catedra;

    public Profesor(){
    }

    public Profesor(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, Double salario, String materia, String catedra) {
        super(nombre, apellido, dni, cargo, anioDeIncorpora, salario);
        this.materia = materia;
        this.catedra = catedra;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }
}
