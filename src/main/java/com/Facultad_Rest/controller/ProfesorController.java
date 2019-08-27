package com.Facultad_Rest.controller;

import com.Facultad_Rest.model.Profesor;
import com.Facultad_Rest.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/empleados/profesores")

public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/{dni}")
    public ResponseEntity buscarProfesor(@PathVariable String dni){
       return profesorService.buscarProfesor(dni);
    }

    @GetMapping("")
    public ResponseEntity mostrarPorfesores(){
        return profesorService.mostrarProfesores();
    }

    @PostMapping("")
    public ResponseEntity agregarPorfesor(@Valid @RequestBody Profesor profesor){
        return profesorService.agregarProfesor(profesor);
    }

    @PutMapping("/{dni}")
    public ResponseEntity actualizarProfesor(@PathVariable String dni,@RequestBody Profesor profesor){
        return profesorService.actualizarProfesor(dni, profesor);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity borrarPorfesor(@PathVariable String dni){
        return profesorService.borrarProfesor(dni);
    }
}
