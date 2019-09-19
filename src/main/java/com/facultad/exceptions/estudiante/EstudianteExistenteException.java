package com.facultad.exceptions.estudiante;

//Excepcion ocurre cuando se quiere crear un estudiante y este ya existe.

public class EstudianteExistenteException extends Exception{
    public EstudianteExistenteException(String mensaje){
        super(mensaje);
    }
}
