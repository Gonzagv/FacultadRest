package com.facultad.service;

import com.facultad.cliente.EmpresaCliente;
import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.model.Empleado;
import com.facultad.model.EmpleadoEmpresa.EmpleadoEmpresa;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Empleado obtenerEmpleado(String dni) throws Exception{
        if (empleadosRepository.existsByDni(dni)) {
            return empleadosRepository.findByDni(dni);
        } else {
            throw new EmpleadoNoExisteException("Empleado no encontrado.");
        }
    }

    public Long borrarEmpleado(String dni) throws Exception{
        if (empleadosRepository.existsByDni(dni)) {
            return empleadosRepository.deleteByDni(dni);
        } else {
            throw new EmpleadoNoExisteException("Empleado no existe.");
        }
    }

    public List<Empleado> mostrarEmpleadosPorNombre(String nombre) {
        return empleadosRepository.findByNombre(nombre);
    }

    public List<Empleado> obtenerEmpleadosPorSalario(Double salarioMin, Double salarioMax) {
        if (salarioMin == null && salarioMax == null) {
            return empleadosRepository.findAll();
        } else {
            if (salarioMin > 0 && salarioMax == null) {
                return empleadosRepository.findBySalario(salarioMin);
            }
            return empleadosRepository.findUsersBySalarioBetween(salarioMin, salarioMax);
        }
    }

    public List<Empleado> buscarEmpleados(Map<String, String> allParams) {
        if (allParams.isEmpty()) {
            return empleadosRepository.findAll();
        } else {
            List<Empleado> lista = new ArrayList<>();
            List<Empleado> lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if (lista.isEmpty()) {
                    if (key.equals("salario")) {
                        String s = allParams.get(key);
                        lista.addAll(empleadosRepository.findByNumero(key, Double.valueOf(s)));
                        if(lista.isEmpty()){
                            return lista;
                        }
                    } else {
                        Object var1 = allParams.get(key);
                        lista.addAll(empleadosRepository.findByParam(key, var1));
                        if(lista.isEmpty()){
                            return lista;
                        }
                    }
                } else {
                    List<Empleado> lista2 = new ArrayList<>();
                    if (key.equals("salario")) {
                        String s = allParams.get(key);
                        lista1.addAll(empleadosRepository.findByNumero(key, Double.valueOf(s)));
                        if(lista1.isEmpty()){
                            return lista;
                        }

                    } else {
                        Object var1 = allParams.get(key);
                        lista1.addAll(empleadosRepository.findByParam(key, var1));
                        if(lista1.isEmpty()){
                            return lista1;
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
            List<Empleado> lista4 = new ArrayList<>(lista3);
            return lista4;
        }
    }

    public List<EmpleadoEmpresa> obtenerEmpleadosDeEmpresa() throws Exception{
        return empresaCliente.obtenerEmpleadosDeEmpresa();
    }

    public EmpleadoEmpresa getEmpleadoDeEmpresa(String dni) throws Exception{
        return empresaCliente.getEmpleadoDeEmpresa(dni);
    }

    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni) throws Exception{
        return empresaCliente.obtenerEmpleadoDeEmpresa(dni);
    }
}
