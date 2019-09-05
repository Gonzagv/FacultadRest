package com.facultad.service;

import com.facultad.model.Empleado;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    public ResponseEntity obtenerEmpleado(String dni) {
        if (empleadosRepository.existsByDni(dni)) {
            return new ResponseEntity(empleadosRepository.findByDni(dni), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity borrarEmpleado(String dni) {
        if (empleadosRepository.existsByDni(dni)) {
            return new ResponseEntity(empleadosRepository.deleteByDni(dni), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity mostrarEmpleadosPorNombre(String nombre) {
        return new ResponseEntity(empleadosRepository.findByNombre(nombre), HttpStatus.OK);
    }

    public ResponseEntity obtenerEmpleadosPorSalario(Double salarioMin, Double salarioMax) {
        if (salarioMin == null && salarioMax == null) {
            return new ResponseEntity(empleadosRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(empleadosRepository.findUsersBySalarioBetween(salarioMin, salarioMax), HttpStatus.OK);
        }
    }

    public ResponseEntity buscarEmpleados(Map<String, Object> allParams) {
        if (allParams.isEmpty()) {
            return new ResponseEntity(empleadosRepository.findAll(), HttpStatus.OK);
        } else {
            List<Empleado> lista = new ArrayList<>();
            for (String key : allParams.keySet()) {
                Object var1 = allParams.get(key);
                lista.addAll(empleadosRepository.findByParam(key,var1));
            }
            Map<String, Empleado> ListaEmpleados= new HashMap<>();
            for (Empleado empleado:lista) {
                ListaEmpleados.put(empleado.getDni(), empleado);
            }
            return new ResponseEntity(ListaEmpleados, HttpStatus.OK);

        }
    }
}
