package com.facultad.controllers.estudiante;

import com.facultad.exceptions.empleado.EmpleadoNoExisteException;
import com.facultad.exceptions.estudiante.EstudianteExistenteException;
import com.facultad.exceptions.estudiante.EstudianteNoExisteException;
import com.facultad.exceptions.estudiante.EstudianteVacioException;
import com.facultad.models.estudiante.Estudiante;
import com.facultad.services.estudiante.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    //Obtiene empleado de la facultad a partir de su dni.

    @GetMapping("/estudiantes/{dni}")
    public ResponseEntity obtenerEstudiante(@PathVariable String dni){
        try {
            return new ResponseEntity(estudianteService.obtenerEstudiante(dni), HttpStatus.OK);
        }catch (EmpleadoNoExisteException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    //Borra estudiante de la facultad a partir de su dni.

    @DeleteMapping("/estudiantes/{dni}")
    public ResponseEntity borrarEstudiante(@PathVariable String dni){
        try{
            return new ResponseEntity(estudianteService.borrarEstudiante(dni), HttpStatus.OK);
        }catch (EmpleadoNoExisteException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    //Crea y agrega a la base un estudiante de la facultad.

    @PostMapping("/estudiantes")
    public ResponseEntity crearEstudiante(@RequestBody Estudiante estudiante){
        try {
            return new ResponseEntity(estudianteService.crearEstudiante(estudiante), HttpStatus.CREATED);
        }catch (EstudianteExistenteException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }catch (EstudianteVacioException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    //Actualiza empleado agregando y/o corrigiendo parametros existentes.

    @PutMapping("/estudiantes")
    public ResponseEntity actualizarEstudiante(@PathVariable String dni, @RequestBody Estudiante estudiante){
        try {
            return new ResponseEntity(estudianteService.actualizarEstudiante(dni, estudiante), HttpStatus.OK);
        }catch (EstudianteVacioException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (EstudianteNoExisteException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /*Obtiene una lista de empleados que coincidan con los parametros ingresados, si no se ingresan devuelve
     la lista de todos lo estudiantes de la facultad.*/

    @GetMapping("/estudiantes")
    public ResponseEntity obtenerEmpleadosPor(@RequestParam(required = false) Map<String, String> allParams) {
        return new ResponseEntity(estudianteService.buscarEstudiantes(allParams), HttpStatus.OK);
    }

}
