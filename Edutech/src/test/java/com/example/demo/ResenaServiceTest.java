package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.model.Resena;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ResenaRepository;
import com.example.demo.service.ResenaService;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private ResenaService resenaService;

    // Test para dejar una reseña
    @Test //27
    void testDejarResena() {
        //Arrange
        Resena resena = new Resena();
        resena.setComentario("Excelente curso");
        resena.setCalificacion(5);

        // Act
        String resultado = resenaService.dejarResena(resena);

        // Assert
        assertEquals("Reseña creada correctamente", resultado);
        verify(resenaRepository).save(resena);

        
    }   

    // Test para asignar una reseña a un curso por sigla
    @Test //28
    void testAsignarResenaCurso_CuandoTodoEsCorrecto() {
        // Arrange
        int resenaId = 1;
        String siglaCurso = "CS101";

        Resena resena = new Resena();
        resena.setId(resenaId);
        resena.setComentario("Muy informativo");
        resena.setCalificacion(4);
        resena.setCurso(null); // Aún no asignada

        Curso curso = new Curso();
        curso.setSigla(siglaCurso);
        curso.setNombre("Curso de Prueba");

        when(resenaRepository.existsById(resenaId)).thenReturn(true);
        when(cursoRepository.existsById(siglaCurso)).thenReturn(true);
        when(resenaRepository.findById(resenaId)).thenReturn(Optional.of(resena));
        when(cursoRepository.findById(siglaCurso)).thenReturn(Optional.of(curso));

        // Act
        String resultado = resenaService.asignarResenaCurso(resenaId, siglaCurso);

        // Assert
        assertEquals("Reseña asignada al curso correctamente", resultado);
        assertEquals(curso, resena.getCurso());
        verify(resenaRepository).save(resena);
    }
    // Test para asignar una reseña a un alumno por rut
    @Test //29
        void testAsignarResenaAlumno_CuandoTodoEsCorrecto() {
        // Arrange
        int resenaId = 1;
        String rutAlumno = "11.111.111-1";

        Resena resena = new Resena();
        resena.setId(resenaId);
        resena.setComentario("Muy buen curso");
        resena.setCalificacion(5);
        resena.setAlumno(null); // Aún no asignada

        Alumno alumno = new Alumno();
        alumno.setRut(rutAlumno);
        alumno.setNombre("Benjamín");
        alumno.setApellido("Burgos");

        when(resenaRepository.existsById(resenaId)).thenReturn(true);
        when(alumnoRepository.existsById(rutAlumno)).thenReturn(true);
        when(resenaRepository.findById(resenaId)).thenReturn(Optional.of(resena));
        when(alumnoRepository.findById(rutAlumno)).thenReturn(Optional.of(alumno));

        // Act
        String resultado = resenaService.asignarResenaAlumno(resenaId, rutAlumno);

        // Assert
        assertEquals("Reseña asignada al alumno correctamente", resultado);
        assertEquals(alumno, resena.getAlumno());
        verify(resenaRepository).save(resena);
    }
    // Test para elminar una reseña por ID
    @Test //30
    void testEliminarResena_CuandoExiste() {
        // Arrange
        int resenaId = 1;
        when(resenaRepository.existsById(resenaId)).thenReturn(true);

        // Act
        String resultado = resenaService.eliminarResena(resenaId);

        // Assert
        assertEquals("Reseña eliminada correctamente", resultado);
        verify(resenaRepository).deleteById(resenaId);
    }

    @Test //31
        void testEliminarResena_CuandoNoExiste() {
            // Arrange
            int resenaId = 2;
            when(resenaRepository.existsById(resenaId)).thenReturn(false);

            // Act
            String resultado = resenaService.eliminarResena(resenaId);

            // Assert
            assertEquals("Reseña no encontrada", resultado);
            verify(resenaRepository, never()).deleteById(resenaId);
        }

}
