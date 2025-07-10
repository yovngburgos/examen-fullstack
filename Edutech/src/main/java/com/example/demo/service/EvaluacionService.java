//Desarrollado por Benjamín Burgos
package com.example.demo.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Curso;
import com.example.demo.model.Evaluacion;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    //Método para crear evaluación

    public String crearEvaluacion(Evaluacion evaluacion) {
        if (evaluacionRepository.findById(evaluacion.getId()).isPresent()) {
            return "La evaluacion ya existe";
        }
        evaluacionRepository.save(evaluacion);
        return "Evaluacion creada correctamente";
    }
    
//Método para listar evaluación

    public List<Evaluacion> listarEvaluacion() {
        return evaluacionRepository.findAll();
    }

//Método para asignar una evaluación a un curso
    public String asignarEvaluacionACurso(String sigla, int id) {
        // Verificar si el curso y la evaluación existen
        if (!evaluacionRepository.existsById(id)) {
            return "Evaluación no encontrada";
        }
        if (!cursoRepository.existsById(sigla)) {
            return "Curso no encontrado";
        }
        // Obtener la evaluación y el curso
        Evaluacion evaluacion = evaluacionRepository.findById(id).get();
        Curso curso = cursoRepository.findById(sigla).get();

        if (evaluacion.getCurso() != null) {
            return "La evaluación ya está asignada a un curso";
        }
        // Asignar la evaluación al curso
        evaluacion.setCurso(curso);
        // Guardar la evaluación actualizada
        evaluacionRepository.save(evaluacion);
        return "Evaluación asignada al curso correctamente";
    }

//Método para listar evaluaciones por curso
    @Autowired
    private CursoRepository cursoRepository;
    public List<Evaluacion> listarEvaluacionesPorCurso(String sigla) {
        if (!cursoRepository.existsById(sigla)) {
            return null; // Curso no encontrado
        }
        Curso curso = cursoRepository.findById(sigla).get();
        return curso.getEvaluaciones(); // Retorna la lista de contenidos del curso
    }
//Método para actualizar una evaluación 

    public Evaluacion actualizarEvaluacion(int id, Evaluacion evaluacionActualizada) {
        Optional<Evaluacion> evaluacionExistente = evaluacionRepository.findById(id);

        if (evaluacionExistente.isPresent()) {
            Evaluacion evaluacion = evaluacionExistente.get();
            evaluacion.setTitulo(evaluacionActualizada.getTitulo());
            evaluacion.setDescripcion(evaluacionActualizada.getDescripcion());
            evaluacion.setTipo(evaluacionActualizada.getTipo());
            evaluacion.setFecha(evaluacionActualizada.getFecha());
            evaluacion.setUrl(evaluacionActualizada.getUrl());
            evaluacion.setNota(evaluacionActualizada.getNota());
            evaluacion.setCorregida(evaluacionActualizada.getCorregida());

            return evaluacionRepository.save(evaluacion);
        } else {
            throw new RuntimeException("Evaluación con ID " + id + " no encontrada.");
        }
    }

    //Método para eliminar una evaluación por ID
    public String eliminarEvaluacion(int id) {
        if (!evaluacionRepository.existsById(id)) {
            return "Evaluación no encontrada";
        }
        evaluacionRepository.deleteById(id);
        return "Evaluación eliminada correctamente";
    }

    
}
