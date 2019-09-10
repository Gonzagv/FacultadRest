package com.facultad.controller;

import com.facultad.model.EmpleadoEmpresa;
import com.facultad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/empleados/sueldo")
    public ResponseEntity BuscarPorSalario(@RequestParam (required = false) Double salario, Double salarioMax){
        return empleadoService.obtenerEmpleadosPorSalario(salario, salarioMax);
    }

    @DeleteMapping("/empleados/{dni}")
    public ResponseEntity borrarEmpleado(@PathVariable String dni){
        return empleadoService.borrarEmpleado(dni);
    }

    @GetMapping("/empleados")
    public ResponseEntity obtenerEmpleadosPor(@RequestParam (required = false) Map<String, String> allParams){
        return empleadoService.buscarEmpleados(allParams);
    }

}
