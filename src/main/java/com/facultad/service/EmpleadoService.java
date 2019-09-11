package com.facultad.service;

import com.facultad.cliente.EmpresaCliente;
import com.facultad.model.Empleado;
import com.facultad.model.EmpleadoEmpresa;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    EmpresaCliente empresaCliente;

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
            if (salarioMin > 0 && salarioMax == null) {
                return new ResponseEntity(empleadosRepository.findBySalario(salarioMin), HttpStatus.OK);
            }
            return new ResponseEntity(empleadosRepository.findUsersBySalarioBetween(salarioMin, salarioMax), HttpStatus.OK);
        }
    }

    public ResponseEntity buscarEmpleados(Map<String, String> allParams) {
        if (allParams.isEmpty()) {
            return new ResponseEntity(empleadosRepository.findAll(), HttpStatus.OK);
        } else {
            List<Empleado> lista = new ArrayList<>();
            List<Empleado> lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if (lista.isEmpty()) {
                    if (key.equals("salario")) {
                        String s = allParams.get(key);
                        lista.addAll(empleadosRepository.findByNumero(key, Double.valueOf(s)));
                        if(lista.isEmpty()){
                            return new ResponseEntity(lista, HttpStatus.OK);
                        }
                    } else {
                        Object var1 = allParams.get(key);
                        lista.addAll(empleadosRepository.findByParam(key, var1));
                        if(lista.isEmpty()){
                            return new ResponseEntity(lista, HttpStatus.OK);
                        }
                    }
                } else {
                    List<Empleado> lista2 = new ArrayList<>();
                    if (key.equals("salario")) {
                        String s = allParams.get(key);
                        lista1.addAll(empleadosRepository.findByNumero(key, Double.valueOf(s)));
                        if(lista1.isEmpty()){
                            return new ResponseEntity(lista, HttpStatus.OK);
                        }

                    } else {
                        Object var1 = allParams.get(key);
                        lista1.addAll(empleadosRepository.findByParam(key, var1));
                        if(lista1.isEmpty()){
                            return new ResponseEntity(lista1, HttpStatus.OK);
                        }
                    }
                    for (Empleado e : lista) {
                        for (int i = 0; i < lista1.size(); i++) {
                            if (lista1.get(i).getDni().contains(e.getDni())) {
                                lista2.add(e);
                            }
                        }
                    }
                    lista = lista2;
                    lista1.removeAll(lista1);
                }
            }

            Set<Empleado> lista3 = lista.stream().collect(Collectors.toSet());
            return new ResponseEntity(lista3, HttpStatus.OK);
        }
    }

    public ResponseEntity obtenerEmpleadosDeEmpresa(){
        return empresaCliente.obtenerEmpleadosDeEmpresa();
    }

    public ResponseEntity getEmpleadoDeEmpresa(String dni){
        return empresaCliente.getEmpleadoDeEmpresa(dni);
    }

    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni){
        return empresaCliente.obtenerEmpleadoDeEmpresa(dni);
    }
}
