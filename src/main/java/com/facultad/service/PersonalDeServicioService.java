package com.facultad.service;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.model.PersonalDeServicio;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonalDeServicioService {

    @Autowired
    EmpleadosRepository empleadosRepository;

    public ResponseEntity buscarPersonalServicio(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.buscarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity listaPersonalServicio(CargoEnum cargo){
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

    public ResponseEntity modificarEmpleado(String dni, PersonalDeServicio personalDeServicio){
        if(personalDeServicio==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            Empleado empleado1 = new PersonalDeServicio(personalDeServicio.getNombre(),personalDeServicio.getApellido(),personalDeServicio.getDni(),personalDeServicio.getCargo(),
                    personalDeServicio.getAnioDeIncorpora(),personalDeServicio.getSalario(),personalDeServicio.getSeccion());
            if(empleado1.getDni().equals(empleadosRepository.buscarEmpleado(dni).getDni())){
                return new ResponseEntity(empleadosRepository.modificarEmpleado(empleado1.getDni(),empleado1), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

    public ResponseEntity borrarEmpleado(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.borrarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
