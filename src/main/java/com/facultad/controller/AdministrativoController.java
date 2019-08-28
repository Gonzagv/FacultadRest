package com.facultad.controller;

import com.facultad.model.Administrativo;
import com.facultad.model.CargoEnum;
import com.facultad.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class AdministrativoController {
    @Autowired
    AdministrativoService administrativoService;

    @GetMapping("/empleados/administrativos")
    public ResponseEntity mostrarEmpleadosAdministrativos(){
        return administrativoService.mostrarEmpleadosAdministrativos(CargoEnum.ADMINISTRATIVO);
    }

    @PostMapping("/empleados/administrativos")
    public ResponseEntity agregarEmpleadoAdministrativo(@Valid @RequestBody Administrativo administrativo){
        return administrativoService.agregarEmpleadoAdministrativo(administrativo);
    }

    @PutMapping("/empleados/administrativos/{dni}")
    public ResponseEntity modificarEmpleadoAdministrativo(@PathVariable String dni, @RequestBody Administrativo administrativo){
        return administrativoService.modificarAdministrativo(dni, administrativo);
    }
}
