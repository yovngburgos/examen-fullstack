//Iván Carrasco
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.IncidenciaDTO;
import com.example.demo.model.Alumno;
import com.example.demo.model.Incidencia;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.IncidenciaRepository;
import com.example.demo.dto.Alumno2DTO;
import java.util.stream.Collectors;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    //Método para crear una incidencia con un alumno
    public String crearIncidencia(Incidencia incidencia, String rut) {
        // Verificar si el alumno existe
        if (!alumnoRepository.existsById(rut)) {
            return "El alumno ingresado no fue encontrado";
        }
        // Obtener el objeto alumno
        Alumno alumno = alumnoRepository.findById(rut).get();
        
        // Asignar el alumno a la incidencia
        incidencia.setAlumno(alumno);
        
        // Guardar la incidencia
        incidenciaRepository.save(incidencia);
        return "Incidencia creada correctamente con el alumno asignado";
    }

    //Método para asignar una incidencia a un alumno por rut
    public String asignarIncidencia(int id, String rut) {
        // Verificar si la incidencia y el alumno existen
        if (!incidenciaRepository.existsById(id)) {
            return "Incidencia no encontrada";
        }
        if(!alumnoRepository.existsById(rut)) {
            return "El alumno ingresado no fue encontrado";
        }
        // Obtener los objetos incidencia y alumno
        Incidencia incidencia = incidenciaRepository.findById(id).get();
        Alumno alumno = alumnoRepository.findById(rut).get();
        
        if (incidencia.getAlumno() != null) {
            return "La incidencia ya está asignada a un alumno";
        }
        //Asignamos incidencia al alumno
        incidencia.setAlumno(alumno);
        // Guardamos la incidencia actualizada
        incidenciaRepository.save(incidencia);
        return "Incidencia asignada al alumno correctamente";
        
    }
    //Método para listar todas las incidencias
/*    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.findAll();
    }
*/  // Método para listar todas las incidencias como DTOs
    public List<IncidenciaDTO> listarIncidenciasDTO() {
    return incidenciaRepository.findAll().stream()
        .map(incidencia -> new IncidenciaDTO(
            incidencia.getId(),
            incidencia.getDescripcion(),
            incidencia.getEstado(),
            incidencia.getFecha(),
            incidencia.getAlumno() != null ? 
            new Alumno2DTO(
                incidencia.getAlumno().getRut(),
                incidencia.getAlumno().getNombre(),
                incidencia.getAlumno().getApellido()
            ): new Alumno2DTO("Sin Asignar", "Desconocido", "Desconocido")
        )).collect(Collectors.toList());
}

    //Método para eliminar una incidencia por ID
    public String eliminarIncidencia(int id) {
        if (incidenciaRepository.existsById(id)) {
            incidenciaRepository.deleteById(id);
            return "Incidencia eliminada correctamente";
        } else {
            return "Incidencia no encontrada";
        }
    }

}
