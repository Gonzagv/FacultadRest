package com.facultad.controller;

import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class EmpresaController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empresa")
    public ResponseEntity obtenerEmpleadosDeEmpresa(){
        try {
            return new ResponseEntity(empleadoService.obtenerEmpleadosDeEmpresa(), HttpStatus.OK);
        }catch (ResourceAccessException e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/empresa/{dni}")
    public ResponseEntity obtenerEmpleadoDeEmpresa(@PathVariable String dni){
        try {
            return new ResponseEntity(empleadoService.getEmpleadoDeEmpresa(dni), HttpStatus.OK);
        }catch (ResourceAccessException e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (EmpleadoNoExisteException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
