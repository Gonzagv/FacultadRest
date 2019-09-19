package com.facultad.exceptions.empleado;

//Excepcion ocurre cuando se quiere crear un empleado y este ya existe.

public class EmpleadoExistenteException extends Exception{
    public EmpleadoExistenteException(String mensaje){
        super(mensaje);
    }
}
