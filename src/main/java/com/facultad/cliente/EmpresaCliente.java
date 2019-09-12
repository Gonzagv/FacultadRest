package com.facultad.cliente;

import com.facultad.exceptions.EmpleadoNoExisteException;
import com.facultad.model.EmpleadoEmpresa.EmpleadoEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpresaCliente {

    @Value("${empresa.host}")
    private String empresaHost;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public List<EmpleadoEmpresa> obtenerEmpleadosDeEmpresa() throws Exception{
        List<EmpleadoEmpresa> lista = new ArrayList<>();
        try{
            ResponseEntity<String> body = restTemplate.getForEntity(empresaHost, String.class);
            lista= objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoEmpresa>>() {});
            return lista;
        }catch(ResourceAccessException e){
            throw new ResourceAccessException("El servidor se encuentra temporalmente inhabilitado.");
        }catch (Exception ex) {
            throw new Exception("Not found.");
        }
    }


    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni) throws Exception{
        String url= empresaHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoEmpresa>() {});
        }catch (Exception ex){
            throw new Exception("Empleado no encontrado.");
        }
    }

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
}
