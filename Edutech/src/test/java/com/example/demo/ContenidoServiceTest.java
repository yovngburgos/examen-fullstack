package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.model.Contenido;
import com.example.demo.repository.ContenidoRepository;
import com.example.demo.service.ContenidoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContenidoServiceTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoService contenidoService;

    @Test
    void testCrearContenido_CuandoNoExiste() {
        // Arrange
        Contenido contenido = new Contenido();
        contenido.setTitulo("POO en Java");

        when(contenidoRepository.findByTitulo("POO en Java")).thenReturn(Optional.empty());

        // Act
        String resultado = contenidoService.crearContenido(contenido);

        // Assert
        assertEquals("Contenido creado correctamente", resultado);
        verify(contenidoRepository).save(contenido);
    }

    @Test
    void testCrearContenido_CuandoYaExiste() {
        // Arrange
        Contenido contenido = new Contenido();
        contenido.setTitulo("POO en Java");

        when(contenidoRepository.findByTitulo("POO en Java")).thenReturn(Optional.of(contenido));

        // Act
        String resultado = contenidoService.crearContenido(contenido);

        // Assert
        assertEquals("El contenido ya existe", resultado);
        verify(contenidoRepository, never()).save(any());
    }
}