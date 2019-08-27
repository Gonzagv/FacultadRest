package com.Facultad_Rest.controller;

import com.Facultad_Rest.model.Profesor;
import com.Facultad_Rest.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/empleados")

public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/profesores/{dni}")
    public ResponseEntity buscarProfesor(@PathVariable String dni){
       return profesorService.buscarProfesor(dni);
    }

    @GetMapping("/profesores")
    public ResponseEntity mostrarPorfesores(){
        return profesorService.mostrarProfesores();
    }

    @PostMapping("/profesores")
    public ResponseEntity agregarPorfesor(@RequestBody Profesor profesor){
        return profesorService.agregarProfesor(profesor);
    }

    @PutMapping("/profesores/{dni}")
    public ResponseEntity actualizarProfesor(@PathVariable String dni,@RequestBody Profesor profesor){
        return profesorService.actualizarProfesor(dni, profesor);
    }

    @DeleteMapping("/profesores/{dni}")
    public ResponseEntity borrarPorfesor(@PathVariable String dni){
        return profesorService.borrarProfesor(dni);
    }
}
