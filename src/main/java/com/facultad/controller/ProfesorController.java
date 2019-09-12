package com.facultad.controller;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.model.CargoEnum;
import com.facultad.model.Profesor;
import com.facultad.service.ProfesorService;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")

public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/empleados/profesores")
    public ResponseEntity mostrarEmpleadosPorfesores(){
        return new ResponseEntity(profesorService.mostrarEmpleadosProfesores(CargoEnum.PROFESOR), HttpStatus.OK);
    }

    @PostMapping("/empleados/profesores")
    public ResponseEntity agregarEmpleadoPorfesor(@Valid @RequestBody Profesor profesor){
        try {
            return new ResponseEntity(profesorService.agregarEmpleadoProfesor(profesor),HttpStatus.OK);
        }catch (EmpleadoExistenteException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }catch (CargoIncorrectoException e){
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/empleados/profesores/{dni}")
    public ResponseEntity actualizarEmpleadoProfesor(@PathVariable String dni, @RequestBody Profesor profesor){
        try {
            return new ResponseEntity(profesorService.actualizarEmpleadoProfesor(dni, profesor), HttpStatus.OK);
        }catch (EmpleadoNoExisteException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (CargoIncorrectoException e){
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/empresa/profesores/{dni}")
    public ResponseEntity crearEmpleadoProfesorDeEmpresa(@PathVariable String dni){
        try {
            return new ResponseEntity(profesorService.crearEmpleadoProfesorDeEmpresa(dni), HttpStatus.CREATED);
        }catch (EmpleadoExistenteException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }catch (EmpleadoNoExisteException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    }
