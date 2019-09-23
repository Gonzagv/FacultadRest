package com.facultad.exceptions.estudiante;

//Excepcion ocurre si el usuario intenta cambiar el Dni de un estudiante.

public class EstudianteCambioDniExcepcion extends Exception {
    public EstudianteCambioDniExcepcion(String mensaje){super(mensaje);}
}
