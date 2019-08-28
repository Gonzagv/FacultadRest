package com.facultad.respository;


import com.facultad.model.CargoEnum;
import com.facultad.model.Empleado;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmpleadosRepository {

    Map<String, Empleado> listaEmpleados = new HashMap<String, Empleado>();

    public Map<String, Empleado> mostrarEmpleados(){
        return listaEmpleados;
    }

    public Empleado buscarEmpleado(String dni) {
        return listaEmpleados.get(dni);
    }

    public Empleado agregarEmpleado(Empleado empleado) {
        listaEmpleados.put(empleado.getDni(), empleado);
        return empleado;
    }

    public Empleado modificarEmpleado(String dni, Empleado empleado) {
        listaEmpleados.put(empleado.getDni(), empleado);
        return empleado;
    }

    public Empleado borrarEmpleado(String dni) {
        return listaEmpleados.remove(dni);
    }

    public Collection<Empleado> listaPorCargo(CargoEnum cargo) {
        return listaEmpleados.values().stream().filter(empleado -> empleado.getCargo().equals(cargo))
                .collect(Collectors.toList());
    }

    public Map<String, Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(Map<String, Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
}
