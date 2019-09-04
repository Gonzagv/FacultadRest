package com.facultad.service;

import com.facultad.model.Empleado;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity obtenerEmpleado(String dni){
        if(empleadosRepository.existsByDni(dni)){
            return new ResponseEntity(empleadosRepository.findByDni(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity borrarEmpleado(String dni){
        if(empleadosRepository.existsByDni(dni)) {
            return new ResponseEntity(empleadosRepository.deleteByDni(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity mostrarEmpleadosPorNombre(String nombre){
        return new ResponseEntity(empleadosRepository.findByNombre(nombre), HttpStatus.OK);
    }

    public ResponseEntity obtenerEmpleadosPorSalario(Double salarioMin, Double salarioMax) {
        if (salarioMin == null && salarioMax == null) {
            return new ResponseEntity(empleadosRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(empleadosRepository.findUsersBySalarioBetween(salarioMin, salarioMax), HttpStatus.OK);
        }
    }
}
