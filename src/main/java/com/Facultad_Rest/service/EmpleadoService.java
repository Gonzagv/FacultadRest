package com.Facultad_Rest.service;

import com.Facultad_Rest.model.Empleado;
import com.Facultad_Rest.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadosRepository empleadosRepository;

    public ResponseEntity buscarEmpleado(String dni){
        Empleado empleado1=empleadosRepository.buscarEmpleado(dni);
        if(empleado1==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(empleado1, HttpStatus.OK);
        }
    }

    public ResponseEntity mostrarEmpleados(){
        return new ResponseEntity(empleadosRepository.mostrarEmpleados(), HttpStatus.OK);
    }

    public ResponseEntity crearEmpleado(Empleado empleado){
        return new ResponseEntity(empleadosRepository.agregarEmpleado(empleado), HttpStatus.OK);
    }

    public ResponseEntity actualizarEmpleado(String dni, Empleado empleado){
        return new ResponseEntity(empleadosRepository.modificarEmpleado(dni, empleado), HttpStatus.OK);
    }

    public ResponseEntity borrarEmpleado(String dni){
        return new ResponseEntity(empleadosRepository.borrarEmpleado(dni),HttpStatus.OK);
    }
}
