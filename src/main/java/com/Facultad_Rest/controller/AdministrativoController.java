package com.Facultad_Rest.controller;

import com.Facultad_Rest.model.Administrativo;
import com.Facultad_Rest.model.CargoEnum;
import com.Facultad_Rest.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/empleados")
public class AdministrativoController {
    @Autowired
    AdministrativoService administrativoService;

    @GetMapping("/administrativos/{dni}")
    public ResponseEntity buscarAdministrativo(@PathVariable String dni){
        return administrativoService.buscarAdministrativo(dni);
    }

    @GetMapping("/administrativos")
    public ResponseEntity mostrarAdministrativos(){
        return administrativoService.mostrarAdministrativos(CargoEnum.ADMINITRATIVO);
    }

    @PostMapping("/administrativos")
    public ResponseEntity agregarAdministrativo(@RequestBody Administrativo administrativo){
        return administrativoService.agregarAdministrativo(administrativo);
    }

    @PutMapping("/administrativos/{dni}")
    public ResponseEntity modificarAdministrativo(@PathVariable String dni, @RequestBody Administrativo administrativo){
        return administrativoService.modificarAdministrativo(dni, administrativo);
    }

    @DeleteMapping("/administrativos/{dni}")
    public ResponseEntity borrarAdministrativo(@PathVariable String dni){
        return administrativoService.borrarAdministrativo(dni);
    }
}
