package com.facultad.services.empleado;

import com.facultad.cliente.EmpresaCliente;
import com.facultad.exceptions.empleado.EmpleadoNoExisteException;
import com.facultad.models.empleado.Empleado;
import com.facultad.models.empresa.EmpleadoEmpresa;
import com.facultad.respository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    EmpresaCliente empresaCliente;

    @Autowired
    private EmpleadosRepository empleadosRepository;

    //Busca un empleado a partir de su dni.

    public Empleado obtenerEmpleado(String dni) throws Exception{
        if (empleadosRepository.existsByDni(dni)) {
            return empleadosRepository.findByDni(dni);
        } else {
            throw new EmpleadoNoExisteException("Empleado no encontrado.");
        }
    }

    //Borra un empleado a partir de su dni.

    public Long borrarEmpleado(String dni) throws Exception{
        if (empleadosRepository.existsByDni(dni)) {
            return empleadosRepository.deleteByDni(dni);
        } else {
            throw new EmpleadoNoExisteException("Empleado no existe.");
        }
    }

    //Busca lista de empleados por nombre.

    public List<Empleado> mostrarEmpleadosPorNombre(String nombre) {
        return empleadosRepository.findByNombre(nombre);
    }

    //Busca empleados por salario entre dos valores: min y max.

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

    /*Busca por parametro o parametros una lista de empleado, en caso de no haber parametros
     devuele la lista de todos los empleados*/

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
                    if(lista2.isEmpty()){return null;}
                    lista = lista2;
                    lista1.removeAll(lista1);
                }
            }

            Set<Empleado> lista3 = lista.stream().collect(Collectors.toSet());
            List<Empleado> lista4 = new ArrayList<>(lista3);
            return lista4;
        }
    }

    //Devuelve un empleado de la empresa por su dni.

    public EmpleadoEmpresa getEmpleadoDeEmpresa(String dni) throws Exception{
        return empresaCliente.getEmpleadoDeEmpresa(dni);
    }

    //Devuelve un empleado de la empresa por su dni.

    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni) throws Exception{
        return empresaCliente.obtenerEmpleadoDeEmpresa(dni);
    }

    /*Devuelve una lista de empleados de la empresa a partir de parametros ingresados por el usuario
      si no hay parametros devuelve la lista completa de empleados de la empresa.*/

    public List<EmpleadoEmpresa> buscarEmpleadosDeEmpresaPorParametros(Map<String, String> allParams) throws Exception{
        if(allParams.isEmpty()){
            return empresaCliente.obtenerEmpleadosDeEmpresa();
        }else{
            return empresaCliente.obtenerEmpleadosDeEmpresaPorParametros(allParams);
        }
    }
}
