package com.facultad.controller;

import com.facultad.service.EmpleadoService;
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
        return empleadoService.obtenerEmpleado(dni);
    }

    @GetMapping("/empleados/nombre")
    public ResponseEntity mostrarEmpleadosPorNombre(@RequestParam String nombre){
        return empleadoService.mostrarEmpleadosPorNombre(nombre);
    }

    @GetMapping("/empleados")
    public ResponseEntity buscarPorSueldo(@RequestParam (required = false) Double salarioMin, Double salarioMax){
        return empleadoService.obtenerEmpleadosPorSalario(salarioMin, salarioMax);
    }

    @DeleteMapping("/empleados/{dni}")
    public ResponseEntity borrarEmpleado(@PathVariable String dni){
        return empleadoService.borrarEmpleado(dni);
    }
}
