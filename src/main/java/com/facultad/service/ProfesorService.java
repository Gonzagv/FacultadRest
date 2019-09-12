package com.facultad.service;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.model.*;
import com.facultad.model.EmpleadoEmpresa.EmpleadoEmpresa;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class ProfesorService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private EmpleadoService empleadoService;

    public List<Empleado> mostrarEmpleadosProfesores(CargoEnum cargo){
        return empleadosRepository.findByCargo(cargo);
    }

    public Empleado agregarEmpleadoProfesor(Profesor profesor)throws Exception{
        if(empleadosRepository.existsByDni(profesor.getDni())){
            throw new EmpleadoExistenteException("El empleado ya existe.");
        }else{
            if(profesor.getCargo().equals(CargoEnum.PROFESOR)) {
                return empleadosRepository.save(profesor);
            }else{
                throw new CargoIncorrectoException("El cargo ingresado no coincide con el empleado que desea crear");
            }
        }
    }

    public Empleado actualizarEmpleadoProfesor(String dni, @NotNull Profesor profesor) throws Exception {
        if (!empleadosRepository.existsByDni(profesor.getDni()) && profesor.getDni().equals(dni)) {
            throw new EmpleadoNoExisteException("Los datos ingresados no coinciden con ningun empleado.");
        } else if (profesor.getCargo().equals(CargoEnum.PROFESOR) && empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.PROFESOR) ) {
            Profesor profesor1 = (Profesor) empleadosRepository.findByDni(dni);
            profesor1.setNombre(profesor.getNombre());
            profesor1.setApellido(profesor.getApellido());
            profesor1.setCatedra(profesor.getCatedra());
            profesor1.setMateria(profesor.getMateria());
            profesor1.setAnioDeIncorpora(profesor.getAnioDeIncorpora());
            profesor1.setSalario(profesor.getSalario());
            return empleadosRepository.save(profesor1);
        } else {
            throw new CargoIncorrectoException("El cargo ingresado no coincide con el empleado que desea crear");
        }
    }

    public Empleado crearEmpleadoProfesorDeEmpresa(String dni) throws Exception{
        if(empleadosRepository.existsByDni(dni)){
            throw new EmpleadoExistenteException("El empelado ya existe.");
        }else{
            EmpleadoEmpresa empleadoEmpresa = empleadoService.obtenerEmpleadoDeEmpresa(dni);
            Profesor profesor = new Profesor();
            profesor.setNombre(empleadoEmpresa.getNombre());
            profesor.setApellido(empleadoEmpresa.getApellido());
            profesor.setDni(empleadoEmpresa.getDni());
            profesor.setCargo(CargoEnum.PROFESOR);
            profesor.setSalario(empleadoEmpresa.getSueldo());
            return empleadosRepository.save(profesor);
        }
    }

}
