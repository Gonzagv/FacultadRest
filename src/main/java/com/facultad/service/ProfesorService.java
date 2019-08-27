package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.model.Profesor;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProfesorService {
    @Autowired
    EmpleadosRepository empleadosRepository;

    public ResponseEntity buscarProfesor(String dni){
        return new ResponseEntity(empleadosRepository.buscarEmpleado(dni), HttpStatus.OK);
    }

    public ResponseEntity mostrarProfesores(){
        return new ResponseEntity(empleadosRepository.listaPorCargo(CargoEnum.PROFESOR),HttpStatus.OK);
    }

    public ResponseEntity agregarProfesor(Profesor profesor){
        if(empleadosRepository.getListaEmpleados().containsKey(profesor.getDni())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else{
            if(profesor.getCargo().equals(CargoEnum.PROFESOR)) {
                return new ResponseEntity(empleadosRepository.agregarEmpleado(profesor), HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity actualizarProfesor(String dni, Profesor profesor){
        if(profesor==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            Empleado profesor1 = new Profesor(profesor.getNombre(),profesor.getApellido(),profesor.getDni(),profesor.getCargo(),
                    profesor.getAnioDeIncorpora(),profesor.getSalario(),profesor.getMateria(),profesor.getCatedra());
            if(profesor1.getDni().equals(empleadosRepository.buscarEmpleado(dni).getDni())){
                return new ResponseEntity(empleadosRepository.modificarEmpleado(profesor1.getDni(),profesor1), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

    public ResponseEntity borrarProfesor(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.borrarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
