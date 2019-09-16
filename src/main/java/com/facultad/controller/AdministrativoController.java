package com.facultad.controller;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.exceptions.EmpleadoVacioException;
import com.facultad.model.Administrativo;
import com.facultad.model.CargoEnum;
import com.facultad.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class AdministrativoController {

    @Autowired
    AdministrativoService administrativoService;

    //Trae la lista de los empleados administrativos

    @GetMapping("/empleados/administrativos")
    public ResponseEntity obtenerEmpleadosAdministrativos() {
        return new ResponseEntity(administrativoService.mostrarEmpleadosAdministrativos(CargoEnum.ADMINISTRATIVO), HttpStatus.OK);
    }

    //Crea empleados administrativos.

    @PostMapping("/empleados/administrativos")
    public ResponseEntity agregarEmpleadoAdministrativo(@Valid @RequestBody Administrativo administrativo) {
        try {
            return new ResponseEntity(administrativoService.agregarEmpleadoAdministrativo(administrativo), HttpStatus.CREATED);
        } catch (EmpleadoExistenteException empleadoExiste) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (CargoIncorrectoException cargoIncorrecto) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (EmpleadoVacioException empleadoVacio) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Actualiza empleados administrativos.

    @PutMapping("/empleados/administrativos/{dni}")
    public ResponseEntity modificarEmpleadoAdministrativo(@PathVariable String dni, @RequestBody Administrativo administrativo) {
        try {
            return new ResponseEntity(administrativoService.modificarAdministrativo(dni, administrativo), HttpStatus.OK);
        } catch (CargoIncorrectoException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (EmpleadoNoExisteException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    //Crea empleados administrativos.

    @PostMapping("/empresa/administrativos/{dni}")
    public ResponseEntity crearEmpleadoAdministrativoDeEmpresa(@PathVariable String dni) {
        try {
            return new ResponseEntity(administrativoService.crearEmpleadoAdministrativoDeEmpresa(dni), HttpStatus.CREATED);
        } catch (EmpleadoExistenteException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
