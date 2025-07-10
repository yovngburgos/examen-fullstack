package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.AlumnoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    @Test //1
    void testRegistrarAlumno_CuandoNoExiste() {
        // Arrange
        Alumno alumno = new Alumno();
        alumno.setRut("12345678-9");
        alumno.setNombre("Pedro");
        alumno.setApellido("López");

        when(alumnoRepository.findById("12345678-9")).thenReturn(Optional.empty());

        // Act
        String resultado = alumnoService.registrarAlumno(alumno);

        // Assert
        assertEquals("Alumno registrado exitosamente.", resultado);
        verify(alumnoRepository).save(alumno);
    }

    @Test //2
    void testRegistrarAlumno_CuandoYaExiste() {
        // Arrange
        Alumno alumno = new Alumno();
        alumno.setRut("12345678-9");

        when(alumnoRepository.findById("12345678-9")).thenReturn(Optional.of(alumno));

        // Act
        String resultado = alumnoService.registrarAlumno(alumno);

        // Assert
        assertEquals("El alumno con RUT 12345678-9 ya existe.", resultado);
        verify(alumnoRepository, never()).save(any());
    }
}
