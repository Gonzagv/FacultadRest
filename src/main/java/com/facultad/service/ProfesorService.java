package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.model.PersonalDeServicio;
import com.facultad.model.Profesor;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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

    public ResponseEntity actualizarEmpleadoProfesor(String dni, @NotNull Profesor profesor) {
        if (!empleadosRepository.existsByDni(profesor.getDni()) && profesor.getDni().equals(dni)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (profesor.getCargo().equals(CargoEnum.PROFESOR) && empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.PROFESOR) ) {
            Profesor profesor1 = (Profesor) empleadosRepository.findByDni(dni);
            profesor1.setNombre(profesor.getNombre());
            profesor1.setApellido(profesor.getApellido());
            profesor1.setCatedra(profesor.getCatedra());
            profesor1.setMateria(profesor.getMateria());
            profesor1.setAnioDeIncorpora(profesor.getAnioDeIncorpora());
            profesor1.setSalario(profesor.getSalario());
            return new ResponseEntity(empleadosRepository.save(profesor1), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}
