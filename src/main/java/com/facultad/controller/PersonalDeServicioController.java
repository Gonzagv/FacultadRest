package com.facultad.controller;

import com.facultad.model.CargoEnum;
import com.facultad.model.PersonalDeServicio;
import com.facultad.service.PersonalDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class PersonalDeServicioController {

    @Autowired
    PersonalDeServicioService personalDeServicioService;

    @GetMapping("/empleados/personaldeservicios")
    public ResponseEntity obtenerEmpleadosPersonalServicio(){
        return personalDeServicioService.listaPersonalServicio(CargoEnum.PERSONAL_DE_SERVICIO);
    }

    @PostMapping("/empleados/personaldeservicios")
    public ResponseEntity agregarEmpleadoPersonalDeServicio(@Valid @RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.agregarPersonalDeServicio(personalDeServicio);
    }

    @PutMapping("/empleados/personaldeservicios/{dni}")
    public ResponseEntity modificarEmpleadoPersonalDeServicio(@PathVariable String dni, @RequestBody PersonalDeServicio personalDeServicio){
        return personalDeServicioService.modificarEmpleadoServicio(dni, personalDeServicio);
    }
}
