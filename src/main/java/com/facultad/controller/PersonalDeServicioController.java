package com.facultad.controller;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.exceptions.EmpleadoVacioException;
import com.facultad.model.CargoEnum;
import com.facultad.model.PersonalDeServicio;
import com.facultad.service.PersonalDeServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class PersonalDeServicioController {

    @Autowired
    PersonalDeServicioService personalDeServicioService;

    //Busca la lista de empleados que pertenecen al personal de servicio.

    @GetMapping("/empleados/personaldeservicios")
    public ResponseEntity obtenerEmpleadosPersonalServicio() {
        return new ResponseEntity(personalDeServicioService.listaPersonalServicio(CargoEnum.PERSONAL_DE_SERVICIO), HttpStatus.OK);
    }

    //Crea empleados del area de personal de servicio.

    @PostMapping("/empleados/personaldeservicios")
    public ResponseEntity agregarEmpleadoPersonalDeServicio(@Valid @RequestBody PersonalDeServicio personalDeServicio) {
        try {
            return new ResponseEntity(personalDeServicioService.agregarPersonalDeServicio(personalDeServicio), HttpStatus.OK);
        } catch (EmpleadoExistenteException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (CargoIncorrectoException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Actualiza un empleado del area de personal de servicio a partir de su Dni.

    @PutMapping("/empleados/personaldeservicios/{dni}")
    public ResponseEntity modificarEmpleadoPersonalDeServicio(@PathVariable String dni, @RequestBody PersonalDeServicio personalDeServicio) {
        try {
            return new ResponseEntity(personalDeServicioService.modificarEmpleadoServicio(dni, personalDeServicio), HttpStatus.OK);
        } catch (CargoIncorrectoException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (EmpleadoNoExisteException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Crea un empleado previamente en Empresa y lo asigna al area de personal de servicio.

    @PostMapping("/empresa/personaldeservicios/{dni}")
    public ResponseEntity crearPersonalDeServicioDeEmpresa(@PathVariable String dni) {
        try {
            return new ResponseEntity(personalDeServicioService.crearPersonalDeServicioDeEmpresa(dni), HttpStatus.OK);
        } catch (EmpleadoExistenteException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (EmpleadoVacioException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
