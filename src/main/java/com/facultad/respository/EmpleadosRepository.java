package com.facultad.respository;

import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public interface EmpleadosRepository extends MongoRepository<Empleado, String> {
    boolean existsByNombre(String nombre);

    @Query("{ 'nombre' : { $eq : ?0 } }")
    List<Empleado> findByNombre(String nombre);

    List<Empleado> findByCargo(CargoEnum cargo);

    Empleado findByDni(String dni);

    boolean existsByDni(String dni);

    Long deleteByDni(String dni);

    @Query("{ 'salario' : { $gte: ?0, $lte: ?1 } }")
    List<Empleado> findUsersBySalarioBetween(Double salarioMin, Double salarioMax);

    @Query("{'?0' :{$eq :?1 }}")
    List<Empleado> findByParam(String key, Object value);
}

