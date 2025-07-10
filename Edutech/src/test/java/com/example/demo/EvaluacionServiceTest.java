package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.model.Curso;
import com.example.demo.model.Evaluacion;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EvaluacionRepository;
import com.example.demo.service.EvaluacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    @Test //7
    void testCrearEvaluacion_CuandoNoExiste() {
        // Arrange
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);
        evaluacion.setTitulo("Evaluación Parcial");

        when(evaluacionRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        String resultado = evaluacionService.crearEvaluacion(evaluacion);

        // Assert
        assertEquals("Evaluacion creada correctamente", resultado);
        verify(evaluacionRepository).save(evaluacion);
    }

    @Test //8
    void testCrearEvaluacion_CuandoYaExiste() {
        // Arrange
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);

        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacion));

        // Act
        String resultado = evaluacionService.crearEvaluacion(evaluacion);

        // Assert
        assertEquals("La evaluacion ya existe", resultado);
        verify(evaluacionRepository, never()).save(any());
    }

    @Test //9
    void testEvaluacionNoExiste() {
        when(evaluacionRepository.existsById(1)).thenReturn(false);

        String resultado = evaluacionService.asignarEvaluacionACurso("INF123", 1);

        assertEquals("Evaluación no encontrada", resultado);
    }

    @Test //10
    void testCursoNoExiste() {
        when(evaluacionRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.existsById("INF123")).thenReturn(false);

        String resultado = evaluacionService.asignarEvaluacionACurso("INF123", 1);

        assertEquals("Curso no encontrado", resultado);
    }

    @Test //11
    void testEvaluacionYaAsignada() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);
        evaluacion.setCurso(new Curso()); // Ya asignada

        when(evaluacionRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.existsById("INF123")).thenReturn(true);
        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacion));
        when(cursoRepository.findById("INF123")).thenReturn(Optional.of(new Curso()));

        String resultado = evaluacionService.asignarEvaluacionACurso("INF123", 1);

        assertEquals("La evaluación ya está asignada a un curso", resultado);
    }

    @Test //12
    void testAsignacionCorrecta() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1);
        evaluacion.setCurso(null); // No asignada

        Curso curso = new Curso();
        curso.setSigla("INF123");

        when(evaluacionRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.existsById("INF123")).thenReturn(true);
        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacion));
        when(cursoRepository.findById("INF123")).thenReturn(Optional.of(curso));

        String resultado = evaluacionService.asignarEvaluacionACurso("INF123", 1);

        assertEquals("Evaluación asignada al curso correctamente", resultado);
        verify(evaluacionRepository).save(evaluacion);
        assertEquals(curso, evaluacion.getCurso());
    }
    
}