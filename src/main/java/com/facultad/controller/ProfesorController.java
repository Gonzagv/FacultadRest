package com.facultad.controller;

import com.facultad.model.Profesor;
import com.facultad.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")

public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/empleados/profesores")
    public ResponseEntity mostrarEmpleadosPorfesores(){
        return profesorService.mostrarEmpleadosProfesores();
    }

    @PostMapping("/empleados/profesores")
    public ResponseEntity agregarEmpleadoPorfesor(@Valid @RequestBody Profesor profesor){
        return profesorService.agregarEmpleadoProfesor(profesor);
    }

    @PutMapping("/empleados/profesores/{dni}")
    public ResponseEntity actualizarEmpleadoProfesor(@PathVariable String dni, @RequestBody Profesor profesor){
        return profesorService.actualizarEmpleadoProfesor(dni, profesor);
    }
}
