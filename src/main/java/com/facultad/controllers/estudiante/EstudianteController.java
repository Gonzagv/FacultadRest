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

    /*@GetMapping("/estudiantes")
    public ResponseEntity obtenerEstudiantes(){
        try {
            return new ResponseEntity(estudianteService.obtenerEstudiantes(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }*/

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

    @GetMapping("/estudiantes")
    public ResponseEntity obtenerEmpleadosPor(@RequestParam(required = false) Map<String, String> allParams) {
        return new ResponseEntity(estudianteService.buscarEstudiantes(allParams), HttpStatus.OK);
    }

}
