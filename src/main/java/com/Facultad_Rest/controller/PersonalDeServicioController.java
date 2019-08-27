package com.Facultad_Rest.controller;

import com.Facultad_Rest.model.CargoEnum;
import com.Facultad_Rest.model.PersonalDeServicio;
import com.Facultad_Rest.service.PersonalDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/empleados/personaldeservicios")
public class PersonalDeServicioController {

    @Autowired
    PersonalDeServicioService personalDeServicioService;

    @GetMapping("/{dni}")
    public ResponseEntity buscarPersonalDeServicio(@PathVariable String dni){
       return personalDeServicioService.buscarPersonalServicio(dni);
    }

    @GetMapping("")
    public ResponseEntity mostrarListaPersonalServicio(){
        return personalDeServicioService.listaPersonalServicio(CargoEnum.PERSONAL_DE_SERVICIO);
    }

    @PostMapping("")
    public ResponseEntity agregarPersonalDeServicio(@Valid @RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.agregarPersonalDeServicio(personalDeServicio);
    }

    @PutMapping("/{dni}")
    public ResponseEntity modificarPersonalDeServicio(@PathVariable String dni, @RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.modificarEmpleado(dni, personalDeServicio);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity borrarPersonalDeServicio(@PathVariable String dni){
        return personalDeServicioService.borrarEmpleado(dni);
    }

}
