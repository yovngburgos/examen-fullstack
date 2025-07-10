package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    //crea un objeto falso que permite controlar su comportamiento
    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test //3
    void registrarCursoRepetido() {
        Curso curso = new Curso ();

        curso.setSigla("A2");
        curso.setNombre("Analista programador");

        when(cursoRepository.findById("A2")).thenReturn(Optional.of(new Curso()));

        String respuesta = cursoService.registrarCurso(curso);

        assertEquals("El curso ya existe", respuesta);
    }

    @Test //4
    void testEliminarCurso_CursoExiste() {
        // Arrange
        String sigla = "INF123";
        Curso cursoMock = new Curso();
        cursoMock.setSigla(sigla);

        when(cursoRepository.findById(sigla)).thenReturn(Optional.of(cursoMock));

        // Act
        String resultado = cursoService.eliminarCurso(sigla);

        // Assert
        assertEquals("Curso eliminado correctamente", resultado);
        verify(cursoRepository, times(1)).deleteById(sigla);
    }

    @Test //5
    void testEliminarCurso_CursoNoExiste() {
        // Arrange
        String sigla = "INF999";
        when(cursoRepository.findById(sigla)).thenReturn(Optional.empty());

        // Act
        String resultado = cursoService.eliminarCurso(sigla);

        // Assert
        assertEquals("El curso no existe", resultado);
        verify(cursoRepository, never()).deleteById(sigla);
    }

    @Test //6
    public void testActualizarCurso_CursoExiste() {
        // Arrange
        String sigla = "INF101";
        Curso curso = new Curso();
        curso.setSigla(sigla);

        when(cursoRepository.findById(sigla)).thenReturn(Optional.of(curso));

        // Act
        String resultado = cursoService.actualizarCurso(curso, sigla);

        // Assert
        assertEquals("Curso actualizado correctamente", resultado);
        verify(cursoRepository, times(1)).save(curso);
    }
    

}


