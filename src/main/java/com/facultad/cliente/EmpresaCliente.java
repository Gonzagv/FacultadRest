package com.facultad.cliente;

import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.modelEmpresa.EmpleadoEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EmpresaCliente {

    @Value("${empresa.host}")
    private String empresaHost;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    //Busca un empleado de la empresa por su Dni.

    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni) throws Exception{
        String url= empresaHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoEmpresa>() {});
        }catch (Exception ex){
            throw new Exception("Empleado no encontrado.");
        }
    }

    public List<EmpleadoEmpresa> obtenerEmpleadosDeEmpresa() throws Exception{
        String url = empresaHost;
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoEmpresa>>() {});
        }catch (Exception ex){
            throw new Exception("Empleado no encontrado.");
        }
    }

    //Busca un empleado de la empresa por su Dni.

    public EmpleadoEmpresa getEmpleadoDeEmpresa(String dni) throws Exception{
        String url= empresaHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            EmpleadoEmpresa empleado = objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoEmpresa>() {});
            return empleado;
        }catch (ResourceAccessException e){
            throw new ResourceAccessException("El servidor se encuentra temporalmente inhabilitado.");
        }catch (Exception ex){
            throw new EmpleadoNoExisteException("Empleado no encontrado.");
        }
    }

    /*
     *Busca por parametro un lista de empleados de la empresa que correspondan con los parametros ingresados por el ususario,
     *en caso de no enviar parametros devuelve la lista de todos los empleados de la empresa.
     */

    public List<EmpleadoEmpresa> obtenerEmpleadosDeEmpresaPorParametros(Map<String,String> allParams) throws Exception{
        try{
            List<EmpleadoEmpresa> listaEmpresa;
            StringBuilder builder = new StringBuilder();
            builder.append(empresaHost+"?");
            for (String key : allParams.keySet()) {
                builder.append(key+"="+allParams.get(key)+"&");
            }
            String url = builder.toString();
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            listaEmpresa = objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoEmpresa>>() {});
            return listaEmpresa;
        }catch (ResourceAccessException e){
            throw new ResourceAccessException("El servidor se encuentra temporalmente inhabilitado.");
        }
    }
}
