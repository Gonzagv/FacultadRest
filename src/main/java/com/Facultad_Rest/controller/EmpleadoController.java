package com.Facultad_Rest.controller;

import com.Facultad_Rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados/{dni}")
    public ResponseEntity buscarEmpleado(@PathVariable String dni){
        return empleadoService.buscarEmpleado(dni);
    }

    @GetMapping("/empleados")
    public ResponseEntity mostrarEmpleados(){
        return empleadoService.mostrarEmpleados();
    }

    @DeleteMapping("/empleados/{dni}")
    public ResponseEntity borrarEmpleado(@PathVariable String dni){
        return empleadoService.borrarEmpleado(dni);
    }

}
