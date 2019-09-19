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
    private String curso;
    private boolean accesoComedor;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isAccesoComedor() {
        return accesoComedor;
    }

    public void setAccesoComedor(boolean accesoComedor) {
        this.accesoComedor = accesoComedor;
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

    public Estudiante(String id, @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.") String nombre, @Pattern(regexp = "^[\\p{L}]+", message = "Nombre no debe contener numeros.") String apellido, @NotNull @NotEmpty @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros") String dni, String curso, boolean accesoComedor) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.curso = curso;
        this.accesoComedor = accesoComedor;
    }

    public Estudiante() {
    }
}
