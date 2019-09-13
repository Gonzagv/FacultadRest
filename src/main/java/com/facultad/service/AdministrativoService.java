package com.facultad.service;

import com.facultad.exceptions.CargoIncorrectoException;
import com.facultad.exceptions.EmpleadoExistenteException;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.exceptions.EmpleadoVacioException;
import com.facultad.model.Administrativo;
import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import com.facultad.modelEmpresa.EmpleadoEmpresa;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class AdministrativoService{

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadosRepository empleadosRepository;

    //Busca la lista de empleados administrativos.

    public List<Empleado> mostrarEmpleadosAdministrativos(CargoEnum cargo){
        return empleadosRepository.findByCargo(cargo);
    }

    //Crea empleado administrativo.

    public Empleado agregarEmpleadoAdministrativo (Administrativo administrativo) throws Exception {
        if (administrativo == null) {
            throw new EmpleadoVacioException("Los Atributos para crear empleado son insuficientes o erroneos.");
        } else {
            if (empleadosRepository.existsByDni(administrativo.getDni())) {
                throw new EmpleadoExistenteException("Ya hay un empleado creado con ese documento.");
            } else {
                if (administrativo.getCargo().equals(CargoEnum.ADMINISTRATIVO)) {
                    empleadosRepository.save(administrativo);
                    return administrativo;
                } else {
                    throw new CargoIncorrectoException("El cargo debe ser administrativo.");
                }
            }
        }

    }

    //Modifica empleado administrativo existente.

    public Administrativo modificarAdministrativo(String dni, @NotNull Administrativo administrativo) throws Exception {
        if(!empleadosRepository.existsByDni(dni) && administrativo.getDni().equals(dni)) {
            throw new EmpleadoNoExisteException("El empleado que desea modificar no existe, verifique los datos");
        }else {
            if (empleadosRepository.findByDni(dni).getCargo().equals(CargoEnum.ADMINISTRATIVO) && administrativo.getCargo().equals(CargoEnum.ADMINISTRATIVO)) {
                Administrativo administrativo1 = (Administrativo) empleadosRepository.findByDni(dni);
                administrativo1.setNombre(administrativo.getNombre());
                administrativo1.setApellido(administrativo.getApellido());
                administrativo1.setSector(administrativo.getSector());
                administrativo1.setAnioDeIncorpora(administrativo.getAnioDeIncorpora());
                administrativo1.setSalario(administrativo.getSalario());
                return empleadosRepository.save(administrativo1);
            } else {
                throw new CargoIncorrectoException("El cargo del empleado guardado no coincide con la informacion que desea actualizar.");
            }
        }
    }

    //Crea empleado administrativo que proviene de la empresa.

    public Empleado crearEmpleadoAdministrativoDeEmpresa(String dni) throws Exception{
        if(empleadosRepository.existsByDni(dni)){
            throw new EmpleadoExistenteException("Ya existe ese empleado.");
        }else {
            EmpleadoEmpresa empleadoEmpresa = empleadoService.obtenerEmpleadoDeEmpresa(dni);
            Administrativo administrativo = new Administrativo();
            administrativo.setNombre(empleadoEmpresa.getNombre());
            administrativo.setApellido(empleadoEmpresa.getApellido());
            administrativo.setDni(empleadoEmpresa.getDni());
            administrativo.setCargo(CargoEnum.ADMINISTRATIVO);
            administrativo.setSalario(empleadoEmpresa.getSueldo());
            return empleadosRepository.save(administrativo);
        }
    }
}
