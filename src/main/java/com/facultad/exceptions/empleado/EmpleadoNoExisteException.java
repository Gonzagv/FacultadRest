package com.facultad.exceptions.empleado;

//Excepcion ocurre cuando el empleado que se esta buscando no existe.

public class EmpleadoNoExisteException extends Exception {
    public EmpleadoNoExisteException(String mensaje){
        super(mensaje);
    }
}
