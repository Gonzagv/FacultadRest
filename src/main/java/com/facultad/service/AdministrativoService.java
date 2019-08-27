package com.facultad.service;

import com.facultad.model.Administrativo;
import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdministrativoService{
    @Autowired
    EmpleadosRepository empleadosRepository;

    public ResponseEntity buscarAdministrativo(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.buscarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity mostrarAdministrativos(CargoEnum cargo){
        return new ResponseEntity(empleadosRepository.listaPorCargo(cargo), HttpStatus.OK);
    }

    public ResponseEntity agregarAdministrativo(Administrativo administrativo){
        if(empleadosRepository.getListaEmpleados().containsKey(administrativo.getDni())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else{
            if(administrativo.getCargo().equals(CargoEnum.ADMINISTRATIVO)) {
                Empleado empleado1 = new Administrativo(administrativo.getNombre(), administrativo.getApellido(), administrativo.getDni(),
                        administrativo.getCargo(), administrativo.getAnioDeIncorpora(), administrativo.getSalario(), administrativo.getSector());
                return new ResponseEntity(empleadosRepository.agregarEmpleado(empleado1), HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity modificarAdministrativo(String dni, Administrativo administrativo){
        if(administrativo==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            Empleado empleado1 = new Administrativo(administrativo.getNombre(),administrativo.getApellido(),administrativo.getDni(),
                    administrativo.getCargo(),administrativo.getAnioDeIncorpora(),administrativo.getSalario(),administrativo.getSector());
            if(empleado1.getDni().equals(empleadosRepository.buscarEmpleado(dni).getDni())){
                return new ResponseEntity(empleadosRepository.modificarEmpleado(empleado1.getDni(),empleado1), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }

    public ResponseEntity borrarAdministrativo(String dni){
        if(empleadosRepository.getListaEmpleados().containsKey(dni)){
            return new ResponseEntity(empleadosRepository.borrarEmpleado(dni), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
