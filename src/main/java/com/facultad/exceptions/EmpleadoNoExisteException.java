package com.facultad.exceptions;

//Excepcion ocurre cuando el empleado que se esta buscando no existe.

public class EmpleadoNoExisteException extends Exception {
    public EmpleadoNoExisteException(String mensaje){
        super(mensaje);
    }
}
