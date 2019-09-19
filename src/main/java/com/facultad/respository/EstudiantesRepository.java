package com.facultad.respository;

import com.facultad.models.empleado.Empleado;
import com.facultad.models.estudiante.Estudiante;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EstudiantesRepository extends MongoRepository<Estudiante, String> {
    Estudiante findByDni(String dni);
    boolean existsByDni(String dni);
    Long deleteByDni(String dni);
    @Query("{'?0' :{$eq :?1 }}")
    List<Estudiante> findByParam(String key, Object value);
}
