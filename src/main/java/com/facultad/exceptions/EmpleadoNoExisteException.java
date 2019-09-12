package com.facultad.exceptions;

public class EmpleadoNoExisteException extends Exception {
    public EmpleadoNoExisteException(String mensaje){
        super(mensaje);
    }
}
