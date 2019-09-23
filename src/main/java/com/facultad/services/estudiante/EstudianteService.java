package com.facultad.services.estudiante;

import com.facultad.exceptions.estudiante.EstudianteCambioDniExcepcion;
import com.facultad.exceptions.estudiante.EstudianteExistenteException;
import com.facultad.exceptions.estudiante.EstudianteNoExisteException;
import com.facultad.exceptions.estudiante.EstudianteVacioException;
import com.facultad.models.estudiante.Estudiante;
import com.facultad.respository.EstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EstudianteService {
    @Autowired
    EstudiantesRepository estudiantesRepository;

    //Obtiene un estudiante de la facultad a partir de su dni.

    public Estudiante obtenerEstudiante(String dni) throws Exception {
        if (estudiantesRepository.existsByDni(dni)) {
            return estudiantesRepository.findByDni(dni);
        } else {
            throw new EstudianteNoExisteException("No existe un estudiante con ese Dni.");
        }
    }

    //Obtiene la lista de todos los estudiantes de la facultad.

    public List<Estudiante> obtenerEstudiantes() throws Exception {
        return estudiantesRepository.findAll();
    }

    //Borra estudiante a partir de su dni.

    public Long borrarEstudiante(String dni) throws Exception {
        if (estudiantesRepository.existsByDni(dni)) {
            return estudiantesRepository.deleteByDni(dni);
        } else {
            throw new EstudianteNoExisteException("No existe el empleado que desea borrar.");
        }
    }

    //Crea y agrega a la base un estudiante de la facultad.

    public Estudiante crearEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante == null) {
            throw new EstudianteVacioException("Los atributos del estudiante son incorrectos.");
        } else {
            if (estudiantesRepository.existsByDni(estudiante.getDni())) {
                throw new EstudianteExistenteException("El estudiante que quiere crear ya existe.");
            } else {
                return estudiantesRepository.save(estudiante);
            }
        }
    }

    //Actualiza agregando y/o cambiando parametros ya existentes.

    public Estudiante actualizarEstudiante(String dni, Estudiante estudiante) throws Exception {
        if (estudiante == null) {
            throw new EstudianteVacioException("Los atributos del estudiante son incorrectos.");
        } else {
            if (estudiantesRepository.existsByDni(estudiante.getDni())) {
                if (estudiante.getDni().equals(dni)) {
                    return estudiantesRepository.save(estudiante);
                } else {
                    throw new EstudianteCambioDniExcepcion("Error, no puede cambiar el dni de un estudiante.");
                }
            } else {
                throw new EstudianteNoExisteException("El estudiante ingresado no existe.");
            }
        }
    }

    //Obtiene una lista a partir de los parametros ingresados, si no se ingresan trae todos los empleados de la facultad.

    public List<Estudiante> buscarEstudiantes(Map<String, String> allParams) {
        if (allParams.isEmpty()) {
            return estudiantesRepository.findAll();
        } else {
            List<Estudiante> lista = new ArrayList<>();
            List<Estudiante> lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if (lista.isEmpty()) {
                    Object var1 = allParams.get(key);
                    lista.addAll(estudiantesRepository.findByParam(key, var1));
                    if (lista.isEmpty()) {
                        return lista;
                    }
                } else {
                    List<Estudiante> lista2 = new ArrayList<>();
                        Object var1 = allParams.get(key);
                        lista1.addAll(estudiantesRepository.findByParam(key, var1));
                        if (lista1.isEmpty()) {
                            return lista1;
                        }
                    for (Estudiante e : lista) {
                        for (int i = 0; i < lista1.size(); i++) {
                            if (lista1.get(i).getDni().contains(e.getDni())) {
                                lista2.add(e);
                            }
                        }
                    }
                    if (lista2.isEmpty()) {
                        return null;
                    }
                    lista = lista2;
                    lista1.removeAll(lista1);
                }
            }

            Set<Estudiante> lista3 = lista.stream().collect(Collectors.toSet());
            List<Estudiante> lista4 = new ArrayList<>(lista3);
            return lista4;
        }
    }

}
