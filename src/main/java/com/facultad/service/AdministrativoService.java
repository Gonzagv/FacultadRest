package com.facultad.service;

import com.facultad.model.Administrativo;
import com.facultad.model.CargoEnum;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AdministrativoService{

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity mostrarEmpleadosAdministrativos(CargoEnum cargo){
        return new ResponseEntity(empleadosRepository.findByCargo(cargo), HttpStatus.OK);
    }

    public ResponseEntity agregarEmpleadoAdministrativo(Administrativo administrativo){
        if(empleadosRepository.existsByDni(administrativo.getId())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }else{
            if(administrativo.getCargo().equals(CargoEnum.ADMINISTRATIVO)) {
                return new ResponseEntity(empleadosRepository.save(administrativo), HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
    }

    public ResponseEntity modificarAdministrativo(String dni, @NotNull Administrativo administrativo) {
        if(!empleadosRepository.existsByDni(dni) && administrativo.getDni().equals(dni)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else {
            if (empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.ADMINISTRATIVO) && administrativo.getCargo().equals(CargoEnum.ADMINISTRATIVO)) {
                Administrativo administrativo1 = (Administrativo) empleadosRepository.findByDni(dni);
                administrativo1.setNombre(administrativo.getNombre());
                administrativo1.setApellido(administrativo.getApellido());
                administrativo1.setSector(administrativo.getSector());
                administrativo1.setAnioDeIncorpora(administrativo.getAnioDeIncorpora());
                administrativo1.setSalario(administrativo.getSalario());
                return new ResponseEntity(empleadosRepository.save(administrativo1), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
    }


}
