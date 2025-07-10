//Benjamín Burgos
package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Alumno2DTO;
import com.example.demo.dto.Curso2DTO;
import com.example.demo.dto.ResenaDTO;
import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.model.Resena;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ResenaRepository;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private CursoRepository cursoRepository;

// Método para dejar una reseña, segundo método que incluye alumno y curso
    public String dejarResena2(Resena resena, String rut, String sigla) {
        // Verificar si el alumno y el curso existen
        if (!alumnoRepository.existsById(rut)) {
            return "El alumno ingresado no fue encontrado";
        }
        if (!cursoRepository.existsById(sigla)) {
            return "El curso ingresado no fue encontrado";
        }
        // Obtener los objetos alumno y curso
        Alumno alumno = alumnoRepository.findById(rut).get();
        Curso curso = cursoRepository.findById(sigla).get();
        
        // Asignar el alumno y el curso a la reseña
        resena.setAlumno(alumno);
        resena.setCurso(curso);
        
        // Guardar la reseña
        resenaRepository.save(resena);
        return "Reseña creada correctamente";
    }

    //Método para dejar una reseña

    public String dejarResena(Resena resena) {
        resenaRepository.save(resena);
        return "Reseña creada correctamente";
    }
// Método para listar todas las reseñas
    //public List<Resena> listarResenas() {
        //return resenaRepository.findAll();
    //}
// Método para listar todas las reseñas como DTOs
    public List<ResenaDTO> listarResenasDTO(){
        return resenaRepository.findAll().stream()
            .map(resena -> new ResenaDTO(
                resena.getId(),
                resena.getComentario(),
                resena.getCalificacion(),
                resena.getFecha(),
                resena.getAlumno() != null ? 
                new Alumno2DTO(
                    resena.getAlumno().getRut(),
                    resena.getAlumno().getNombre(),
                    resena.getAlumno().getApellido()
                ): new Alumno2DTO("Sin Asignar", "Desconocido", "Desconocido"),
                resena.getCurso() != null ?
                new Curso2DTO(
                    resena.getCurso().getSigla(),
                    resena.getCurso().getNombre()
                ) : new Curso2DTO("Sin Asignar", "Desconocido")
            ))
            .collect(Collectors.toList());
    }
    
    // Método para asignar una reseña a un alumno por rut
    public String asignarResenaAlumno(int id, String rut) {
        // Verificar si la reseña y el alumno existen
        if (!resenaRepository.existsById(id)) {
            return "Reseña no encontrada";
        }
        if (!alumnoRepository.existsById(rut)) {
            return "El alumno ingresado no fue encontrado";
        }
        // Obtener los objetos reseña y alumno
        Resena resena = resenaRepository.findById(id).get();
        Alumno alumno = alumnoRepository.findById(rut).get();

        if (resena.getAlumno() != null) {
            return "La reseña ya está asignada a un alumno";
        }
        // Asignamos reseña al alumno
        resena.setAlumno(alumno);
        // Guardamos la reseña actualizada
        resenaRepository.save(resena);
        return "Reseña asignada al alumno correctamente";
    }

    // Método para asignar una reseña a un curso por sigla
    public String asignarResenaCurso(int id, String sigla) {
        // Verificar si la reseña y el curso existen
        if (!resenaRepository.existsById(id)) {
            return "Reseña no encontrada";
        }
        if (!cursoRepository.existsById(sigla)) {
            return "El curso ingresado no fue encontrado";
        }
        // Obtener los objetos reseña y curso
        Resena resena = resenaRepository.findById(id).get();
        Curso curso = cursoRepository.findById(sigla).get();
        if (resena.getCurso() != null) {
            return "La reseña ya está asignada a un curso";
        }
        // Asignamos reseña al curso
        resena.setCurso(curso);
        // Guardamos la reseña actualizada
        resenaRepository.save(resena);
        return "Reseña asignada al curso correctamente";
    }

    // Método para listar reseñas por curso
    public List<Resena> listarResenasPorCurso(String sigla) {
        if (!cursoRepository.existsById(sigla)) {
            return null; // Curso no encontrado
        }
        Curso curso = cursoRepository.findById(sigla).get();
        return curso.getResenas(); // Retorna la lista de reseñas del curso
    }

    //Método para eliminar una reseña por ID
    public String eliminarResena(int id) {
        if (!resenaRepository.existsById(id)) {
            return "Reseña no encontrada";
        }
        resenaRepository.deleteById(id);
        return "Reseña eliminada correctamente";
    }

}
