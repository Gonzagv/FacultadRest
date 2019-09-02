package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.model.Profesor;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;


@Service
public class ProfesorService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity mostrarEmpleadosProfesores(CargoEnum cargo){
        return new ResponseEntity(empleadosRepository.findByCargo(cargo),HttpStatus.OK);
    }

    public ResponseEntity agregarEmpleadoProfesor(Profesor profesor){
        if(empleadosRepository.existsByDni(profesor.getDni())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else{
            if(profesor.getCargo().equals(CargoEnum.PROFESOR)) {
                return new ResponseEntity(empleadosRepository.save(profesor), HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity actualizarEmpleadoProfesor(String dni, @NotNull Profesor profesor){
        if(!empleadosRepository.existsByDni(profesor.getDni())){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            if(profesor.getDni().equals(empleadosRepository.findByDni(dni).getDni())){
                empleadosRepository.deleteByDni(dni);
                return new ResponseEntity(empleadosRepository.save(profesor), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }
}
