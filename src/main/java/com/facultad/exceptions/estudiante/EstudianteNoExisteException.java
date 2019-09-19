package com.facultad.exceptions.estudiante;

//Excepcion ocurre cuando el estudiante que se esta buscando no existe.

public class EstudianteNoExisteException extends Exception {
    public EstudianteNoExisteException(String mensaje){
        super(mensaje);
    }
}
