package com.facultad.controller;

import com.facultad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/empleados")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/{dni}")
    public ResponseEntity buscarEmpleado(@PathVariable String dni){
        return empleadoService.buscarEmpleado(dni);
    }

    @GetMapping("")
    public ResponseEntity mostrarEmpleados(){
        return empleadoService.mostrarEmpleados();
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity borrarEmpleado(@PathVariable String dni){
        return empleadoService.borrarEmpleado(dni);
    }

}
