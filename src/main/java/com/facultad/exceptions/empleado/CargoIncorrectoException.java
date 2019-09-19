package com.facultad.exceptions.empleado;

//Excepcion ocurre cuando el cargo ingresado por el usuario no concuerda con cargo del empleado.

public class CargoIncorrectoException extends Exception {
    public CargoIncorrectoException(String mensaje){
        super(mensaje);
    }
}
