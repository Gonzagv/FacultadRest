package com.facultad.controller;

import com.facultad.model.EmpleadoEmpresa;
import com.facultad.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class EmpresaController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empresa")
    public List<EmpleadoEmpresa> obtenerEmpleadosDeEmpresa(){
        return empleadoService.obtenerEmpleadosDeEmpresa();
    }

    @GetMapping("/empresa/{dni}")
    public EmpleadoEmpresa obtenerEmpleadoDeEmpresa(@PathVariable String dni){
        return empleadoService.obtenerEmpleadoDeEmpresa(dni);
    }

}
