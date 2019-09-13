package com.facultad.service;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.exceptions.EmpleadoVacioException;
import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.modelEmpresa.EmpleadoEmpresa;
import com.facultad.model.PersonalDeServicio;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PersonalDeServicioService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private EmpleadoService empleadoService;

    //Devuelve una lista de todos los empleados del personal de servicio.

    public List<Empleado> listaPersonalServicio(CargoEnum cargo) {
        return empleadosRepository.findByCargo(cargo);
    }

    //Crea y agrega un empleado al personal de servicio.

    public Empleado agregarPersonalDeServicio(PersonalDeServicio personalDeServicio) throws Exception {
        if (empleadosRepository.existsByDni(personalDeServicio.getDni())) {
            throw new EmpleadoExistenteException("El empleado ya existe.");
        } else {
            if (personalDeServicio.getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)) {
                return empleadosRepository.save(personalDeServicio);
            } else {
                throw new CargoIncorrectoException("El cargo es incorrecto, verifique los datos ingresados.");
            }
        }
    }

    //Modifica o actualiza un empleado del personal de servicio.

    public Empleado modificarEmpleadoServicio(String dni, @NotNull PersonalDeServicio personalDeServicio) throws Exception {
        if (!empleadosRepository.existsByDni(personalDeServicio.getDni()) && personalDeServicio.getDni().equals(dni)) {
            throw new EmpleadoNoExisteException("Los datos ingresados no coinciden con nungun empleado.");
        } else if (empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)
                && personalDeServicio.getCargo().equals(CargoEnum.PERSONAL_DE_SERVICIO)) {
            PersonalDeServicio personalDeServicio1 = (PersonalDeServicio) empleadosRepository.findByDni(dni);
            personalDeServicio1.setNombre(personalDeServicio.getNombre());
            personalDeServicio1.setApellido(personalDeServicio.getApellido());
            personalDeServicio1.setSeccion(personalDeServicio.getSeccion());
            personalDeServicio1.setAnioDeIncorpora(personalDeServicio.getAnioDeIncorpora());
            personalDeServicio1.setSalario(personalDeServicio.getSalario());
            return empleadosRepository.save(personalDeServicio1);
        }
        throw new CargoIncorrectoException("El cargo ingresado es incorrecto.");
    }

    //Crea un empleado al personal de servicio que proviene de la empresa.

    public Empleado crearPersonalDeServicioDeEmpresa(String dni) throws Exception {
        if (empleadosRepository.existsByDni(dni)) {
            throw new EmpleadoExistenteException("Ya existe un empleado con esas caracteristicas.");
        } else {
            EmpleadoEmpresa empleadoEmpresa = empleadoService.obtenerEmpleadoDeEmpresa(dni);
            if(empleadoEmpresa==null){
                throw new EmpleadoVacioException("Empleado con atributos insuficientes.");
            }else {
                PersonalDeServicio personalDeServicio = new PersonalDeServicio();
                personalDeServicio.setNombre(empleadoEmpresa.getNombre());
                personalDeServicio.setApellido(empleadoEmpresa.getApellido());
                personalDeServicio.setCargo(CargoEnum.PERSONAL_DE_SERVICIO);
                personalDeServicio.setDni(empleadoEmpresa.getDni());
                personalDeServicio.setSalario(empleadoEmpresa.getSueldo());
                empleadosRepository.save(personalDeServicio);
                return personalDeServicio;
            }
        }
    }


}