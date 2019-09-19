package com.facultad.models.empresa;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoEmpresa {

    @Pattern(regexp = "^[\\p{L}]+")
    private String nombre;
    @Pattern(regexp = "^[\\p{L}]+")
    private String apellido;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros")
    private String dni;
    private String telefono;
    private Double sueldo;
    private CargoEnumEmpresa cargo;
    private String despacho;
    private String secretarioDni;
    private String fax;
    private Double comisiones;


    public EmpleadoEmpresa(@Pattern(regexp = "^[\\p{L}]+") String nombre, @Pattern(regexp = "^[\\p{L}]+") String apellido, @NotNull @NotEmpty @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros") String dni, String telefono, Double sueldo, CargoEnumEmpresa cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    public EmpleadoEmpresa() {
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public CargoEnumEmpresa getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnumEmpresa cargo) {
        this.cargo = cargo;
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
