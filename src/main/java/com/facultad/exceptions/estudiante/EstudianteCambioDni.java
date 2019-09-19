package com.facultad.exceptions.estudiante;

//Excepcion ocurre si el usuario intenta cambiar el Dni de un estudiante.

public class EstudianteCambioDni extends Exception {
    public EstudianteCambioDni(String mensaje){super(mensaje);}
}
