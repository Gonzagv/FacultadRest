package com.facultad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoEmpresa {
    private String dni;
    private String nombre;
    private String apellido;

        public EmpleadoEmpresa(String dni, String nombre, String apellido) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public EmpleadoEmpresa() {
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
}
