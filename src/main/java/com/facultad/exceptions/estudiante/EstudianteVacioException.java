package com.facultad.exceptions.estudiante;

//Excepcion ocurre cuando el usuario ingresa mal los atributos de un estudiante para crear o actualizar.

public class EstudianteVacioException extends Exception {
    public EstudianteVacioException(String mensaje){
        super(mensaje);
    }
}
