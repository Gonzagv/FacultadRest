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
        return new ResponseEntity(empleadosRepository.listaPorCargo(cargo), HttpStatus.OK);
    }

    public ResponseEntity agregarPersonalDeServicio(PersonalDeServicio personalDeServicio) {
        if (empleadosRepository.getListaEmpleados().containsKey(personalDeServicio.getDni())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            if (personalDeServicio.getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)) {
                Empleado empleado1 = new PersonalDeServicio(personalDeServicio.getNombre(), personalDeServicio.getApellido(), personalDeServicio.getDni(), personalDeServicio.getCargo(),
                        personalDeServicio.getAnioDeIncorpora(), personalDeServicio.getSalario(), personalDeServicio.getSeccion());
                return new ResponseEntity(empleadosRepository.agregarEmpleado(empleado1), HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity modificarEmpleadoServicio(String dni, @NotNull PersonalDeServicio personalDeServicio) {
        if (!empleadosRepository.getListaEmpleados().containsKey(personalDeServicio.getDni())) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if (personalDeServicio.getDni().equals(empleadosRepository.buscarEmpleado(dni).getDni())){
                Empleado empleado1 = new PersonalDeServicio(personalDeServicio.getNombre(), personalDeServicio.getApellido(), personalDeServicio.getDni(), personalDeServicio.getCargo(),
                        personalDeServicio.getAnioDeIncorpora(), personalDeServicio.getSalario(), personalDeServicio.getSeccion());
                return new ResponseEntity(empleadosRepository.modificarEmpleado(empleado1.getDni(), empleado1), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

}
