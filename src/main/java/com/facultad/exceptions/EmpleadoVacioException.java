package com.facultad.exceptions;

//Excepcion ocurre cuando el usuario ingresa mal los atributos de un empleado para crear o actualizar.

public class EmpleadoVacioException extends Exception {
    public EmpleadoVacioException(String mensaje){
        super(mensaje);
    }
}
