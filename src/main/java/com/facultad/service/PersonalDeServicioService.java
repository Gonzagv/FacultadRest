package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.EmpleadoEmpresa;
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

    @Autowired
    private EmpleadoService empleadoService;

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
        if (!empleadosRepository.existsByDni(personalDeServicio.getDni()) && personalDeServicio.getDni().equals(dni)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)
                && personalDeServicio.getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)) {
            PersonalDeServicio personalDeServicio1 = (PersonalDeServicio) empleadosRepository.findByDni(dni);
            personalDeServicio1.setNombre(personalDeServicio.getNombre());
            personalDeServicio1.setApellido(personalDeServicio.getApellido());
            personalDeServicio1.setSeccion(personalDeServicio.getSeccion());
            personalDeServicio1.setAnioDeIncorpora(personalDeServicio.getAnioDeIncorpora());
            personalDeServicio1.setSalario(personalDeServicio.getSalario());
            return new ResponseEntity(empleadosRepository.save(personalDeServicio1), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    public ResponseEntity crearPersonalDeServicioDeEmpresa(String dni) {
        if (empleadosRepository.existsByDni(dni)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            EmpleadoEmpresa empleadoEmpresa = empleadoService.obtenerEmpleadoDeEmpresa(dni);
            if(empleadoEmpresa==null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }else {
                PersonalDeServicio personalDeServicio = new PersonalDeServicio();
                personalDeServicio.setNombre(empleadoEmpresa.getNombre());
                personalDeServicio.setApellido(empleadoEmpresa.getApellido());
                personalDeServicio.setCargo(CargoEnum.PERSONAL_DE_SERVICIO);
                personalDeServicio.setDni(empleadoEmpresa.getDni());
                return new ResponseEntity(empleadosRepository.save(personalDeServicio), HttpStatus.CREATED);
            }
        }
    }


}