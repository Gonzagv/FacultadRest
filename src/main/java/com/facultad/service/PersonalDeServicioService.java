package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.model.PersonalDeServicio;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class PersonalDeServicioService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity listaPersonalServicio(CargoEnum cargo) {
        return new ResponseEntity(empleadosRepository.findByCargo(cargo), HttpStatus.OK);
    }

    public ResponseEntity agregarPersonalDeServicio(PersonalDeServicio personalDeServicio) {
        if (empleadosRepository.existsByDni(personalDeServicio.getDni())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            if (personalDeServicio.getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)) {
                return new ResponseEntity(empleadosRepository.save(personalDeServicio), HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity modificarEmpleadoServicio(String dni, @NotNull PersonalDeServicio personalDeServicio) {
        if (!empleadosRepository.existsByDni(personalDeServicio.getDni())) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if (personalDeServicio.getDni().equals(empleadosRepository.findByDni(dni).getDni())){
                empleadosRepository.deleteByDni(dni);
                return new ResponseEntity(empleadosRepository.save(personalDeServicio), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

}
