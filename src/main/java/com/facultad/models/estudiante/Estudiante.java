package com.facultad.models.estudiante;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "estudiante")
public class Estudiante {
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
    private String carrera;
    private Integer materiasAprobadas;

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(Integer materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    public Estudiante(String id, @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.") String nombre, @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.") String apellido, @NotNull @NotEmpty @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros") String dni, String carrera, Integer materiasAprobadas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.carrera = carrera;
        this.materiasAprobadas = materiasAprobadas;
    }

    public Estudiante() {
    }
}
