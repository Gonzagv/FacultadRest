package com.Facultad_Rest.controller;

import com.Facultad_Rest.model.CargoEnum;
import com.Facultad_Rest.model.PersonalDeServicio;
import com.Facultad_Rest.service.PersonalDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/empleados")
public class PersonalDeServicioController {

    @Autowired
    PersonalDeServicioService personalDeServicioService;

    @GetMapping("/personaldeservicios/{dni}")
    public ResponseEntity buscarPersonalDeServicio(@PathVariable String dni){
       return personalDeServicioService.buscarPersonalServicio(dni);
    }

    @GetMapping("/personaldeservicios")
    public ResponseEntity mostrarListaPersonalServicio(){
        return personalDeServicioService.listaPersonalServicio(CargoEnum.PERSONAL_DE_SERVICIO);
    }

    @PostMapping("/personaldeservicios")
    public ResponseEntity agregarPersonalDeServicio(@RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.agregarPersonalDeServicio(personalDeServicio);
    }

    @PutMapping("/personaldeservicios/{dni}")
    public ResponseEntity modificarPersonalDeServicio(@PathVariable String dni, @RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.modificarEmpleado(dni, personalDeServicio);
    }

    @DeleteMapping("/personaldeservicios/{dni}")
    public ResponseEntity borrarPersonalDeServicio(@PathVariable String dni){
        return personalDeServicioService.borrarEmpleado(dni);
    }

}
