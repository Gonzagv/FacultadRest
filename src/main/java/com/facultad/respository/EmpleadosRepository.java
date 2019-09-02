package com.facultad.respository;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface EmpleadosRepository extends MongoRepository<Empleado, String> {
    List<Empleado> findByCargo(CargoEnum cargo);
    Empleado findByDni(String dni);
    boolean existsByDni(String dni);
    Long deleteByDni(String dni);
}
