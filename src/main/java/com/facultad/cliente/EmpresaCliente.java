package com.facultad.cliente;

import com.facultad.model.EmpleadoEmpresa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

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

    public ResponseEntity obtenerEmpleadosDeEmpresa(){
        List<EmpleadoEmpresa> lista = new ArrayList<>();
        try{
            ResponseEntity<String> body = restTemplate.getForEntity(empresaHost, String.class);
            lista= objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoEmpresa>>() {});
            return new ResponseEntity(lista, HttpStatus.OK);
        }catch(ResourceAccessException e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(String dni){
        String url= empresaHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoEmpresa>() {});
        }catch (Exception ex){
            return null;
        }
    }

    public ResponseEntity<EmpleadoEmpresa> getEmpleadoDeEmpresa(String dni){
        String url= empresaHost.concat(dni);
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
            EmpleadoEmpresa empleado = objectMapper.readValue(body.getBody(), new TypeReference<EmpleadoEmpresa>() {});
            return new ResponseEntity(empleado, HttpStatus.OK);
        }catch (ResourceAccessException e){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
