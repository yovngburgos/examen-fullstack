package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Curso;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.service.InstructorService;



@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private InstructorService instructorService;

    @Test //13
    void testRegistrarInstructor_CuandoNoExiste() {
        // Arrange
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setNombre("Carlos");
        instructor.setApellido("Muñoz");
        instructor.setEmail("carlos@example.com");
        instructor.setContrasena("1234");
        instructor.setEspecialidad("Matemáticas");

        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.empty());

        // Act
        String resultado = instructorService.registrarInstructor(instructor);

        // Assert
        assertEquals("Instructor registrado correctamente", resultado);
        verify(instructorRepository).save(instructor);
    }

    @Test //14
    void testRegistrarInstructor_CuandoYaExiste() {
        // Arrange
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");

        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.of(instructor));

        // Act
        String resultado = instructorService.registrarInstructor(instructor);

        // Assert
        assertEquals("El instructor ya existe", resultado);
        verify(instructorRepository, never()).save(any());
    }

    @Test //15
    void testCursoNoExiste() {
        when(cursoRepository.existsById("INF123")).thenReturn(false);

        String resultado = instructorService.instructorAsignarCurso("12345678-9", "INF123");

        assertEquals("El curso ingresado no fue encontrado", resultado);
    }

    @Test //16
    void testInstructorNoExiste() {
        when(cursoRepository.existsById("INF123")).thenReturn(true);
        when(instructorRepository.existsById("12345678-9")).thenReturn(false);

        String resultado = instructorService.instructorAsignarCurso("12345678-9", "INF123");

        assertEquals("El instructor ingresado no fue encontrado", resultado);
    }

    @Test //17
    void testCursoYaTieneInstructor() {
        Curso curso = new Curso();
        curso.setSigla("INF123");
        curso.setInstructor(new Instructor()); // ya tiene instructor

        when(cursoRepository.existsById("INF123")).thenReturn(true);
        when(instructorRepository.existsById("12345678-9")).thenReturn(true);
        when(cursoRepository.findById("INF123")).thenReturn(Optional.of(curso));
        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.of(new Instructor()));

        String resultado = instructorService.instructorAsignarCurso("12345678-9", "INF123");

        assertEquals("El curso ya tiene un instructor asignado", resultado);
    }

    @Test //18
    void testAsignacionCorrecta() {
        Curso curso = new Curso();
        curso.setSigla("INF123");
        curso.setInstructor(null); // aún no tiene instructor

        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");

        when(cursoRepository.existsById("INF123")).thenReturn(true);
        when(instructorRepository.existsById("12345678-9")).thenReturn(true);
        when(cursoRepository.findById("INF123")).thenReturn(Optional.of(curso));
        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.of(instructor));

        String resultado = instructorService.instructorAsignarCurso("12345678-9", "INF123");

        assertEquals("Instructor asignado al curso correctamente", resultado);
        verify(cursoRepository).save(curso);
        assertEquals(instructor, curso.getInstructor());
    }

    
}


