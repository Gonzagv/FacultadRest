package com.facultad.service;

import com.facultad.model.Empleado;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity obtenerEmpleado(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.buscarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity mostrarEmpleados(){
        return new ResponseEntity(empleadosRepository.mostrarEmpleados(), HttpStatus.OK);
    }

    public ResponseEntity borrarEmpleado(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)) {
            return new ResponseEntity(empleadosRepository.borrarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
