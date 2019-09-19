package com.facultad.controllers.empresa;

import com.facultad.exceptions.empleado.EmpleadoNoExisteException;
import com.facultad.services.empleado.EmpleadoService;
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

    //Busca empleado de Empresa a partir de su Dni.

    @GetMapping("/empresa/{dni}")
    public ResponseEntity obtenerEmpleadoDeEmpresa(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.getEmpleadoDeEmpresa(dni), HttpStatus.OK);
        } catch (ResourceAccessException e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (EmpleadoNoExisteException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Busca lista de empleados de empresa por parametros, si no hay parametros trae la lista completa de empleados de empresa.

    @GetMapping("/empresa")
    public ResponseEntity obtenerEmpleadosDeEmpresaPor(@RequestParam(required = false) Map<String, String> allParams) {
        try {
            return new ResponseEntity(empleadoService.buscarEmpleadosDeEmpresaPorParametros(allParams), HttpStatus.OK);
        }catch (ResourceAccessException e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
