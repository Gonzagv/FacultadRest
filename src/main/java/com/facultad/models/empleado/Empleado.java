package com.facultad.models.empleado;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "empleado")
public class Empleado {
    @Id
    private String id;
    @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.")
    private String nombre;
    @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.")
    private String apellido;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros")
    private String dni;
    private CargoEnum cargo;
    private String anioDeIncorpora;
    private Double salario;

    public Empleado(){}

    public Empleado(String nombre, String apellido, String dni, CargoEnum cargo, String anioDeIncorpora, Double salario) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
        this.anioDeIncorpora = anioDeIncorpora;
        this.salario = salario;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getNombre() { return nombre; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public String getAnioDeIncorpora() {
        return anioDeIncorpora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAnioDeIncorpora(String anioDeIncorpora) {
        this.anioDeIncorpora = anioDeIncorpora;
    }

}
