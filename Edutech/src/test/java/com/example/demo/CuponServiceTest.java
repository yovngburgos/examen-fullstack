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

import com.example.demo.model.Cupon;
import com.example.demo.repository.CuponRepository;
import com.example.demo.service.CuponService;

@ExtendWith(MockitoExtension.class)
public class CuponServiceTest {
    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    @Test //19
    void testCrearCupon_CuandoNoExiste() {
        
        // Arrange
        Cupon cupon = new Cupon();
        cupon.setCodigo("CUP123");
        cupon.setDescuento(20);
        cupon.setCantidadUsos(5);
        cupon.setActivo("true");

        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.empty());

        // Act
        String resultado = cuponService.crearCupon(cupon);

        // Assert
        assertEquals("Cupón CUP123 creado exitosamente.", resultado);
        verify(cuponRepository).save(cupon);
    }

    @Test //20
    void testCrearCupon_CuandoYaExiste() {
        
        // Arrange
        Cupon cupon = new Cupon();
        cupon.setCodigo("CUP123");
        cupon.setDescuento(20);
        cupon.setCantidadUsos(5);
        cupon.setActivo("true");

        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.of(cupon));

        // Act
        String resultado = cuponService.crearCupon(cupon);

        // Assert
        assertEquals("El cupón con ID CUP123 ya existe.", resultado);
        verify(cuponRepository, never()).save(cupon);
    }

    // Test para validar un cupón existente y activo
    @Test //21
    void testValidarCupon_CuandoExisteYActivo() {
        // Arrange
        Cupon cupon = new Cupon();
        cupon.setCodigo("CUP123");
        cupon.setActivo("true");

        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.of(cupon));

        // Act
        String resultado = cuponService.validarCupon("CUP123");

        // Assert
        assertEquals("El cupón CUP123 es válido.", resultado);
        verify(cuponRepository).findById("CUP123");

    }

    // Test para validar un cupón existente pero no activo
    @Test //22
    void testValidarCupon_CuandoExistePeroNoActivo() {
        // Arrange
        Cupon cupon = new Cupon();
        cupon.setCodigo("CUP123");
        cupon.setActivo("false");

        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.of(cupon));

        // Act
        String resultado = cuponService.validarCupon("CUP123");

        // Assert
        assertEquals("El cupón CUP123 no está activo.", resultado);
        verify(cuponRepository).findById("CUP123");
    }
    // Test para validar un cupón que no existe
    @Test //23
    void testValidarCupon_CuandoNoExiste() {
        // Arrange
        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.empty());

        // Act
        String resultado = cuponService.validarCupon("CUP123");
        
        // Assert
        assertEquals("El código con ID CUP123 no existe.", resultado);
        verify(cuponRepository).findById("CUP123");
    }

    //Test para obtener un cupón por código
    @Test //24
    void testObtenerCuponPorCodigo_CuandoExiste() {
        // Arrange
        Cupon cupon = new Cupon();
        cupon.setCodigo("CUP123");

        when(cuponRepository.findById("CUP123"))
            .thenReturn(Optional.of(cupon));

        // Act
        Cupon resultado = cuponService.obtenerCuponPorCodigo("CUP123");

        // Assert
        assertEquals("CUP123", resultado.getCodigo());
        verify(cuponRepository).findById("CUP123");
    }

    // Test para eliminar un cupón existente por código
    @Test //25
    void testEliminarCupon_CuandoExiste() {
        // Arrange
        String codigo = "CUP123";
        when(cuponRepository.findById(codigo))
            .thenReturn(Optional.of(new Cupon()));

        // Act
        String resultado = cuponService.eliminarCupon(codigo);

        // Assert
        assertEquals("Cupón con código CUP123 eliminado exitosamente.", resultado);
        verify(cuponRepository).deleteById(codigo);
    }

    // Test para eliminar un cupón que no existe
    @Test //26
    void testEliminarCupon_CuandoNoExiste() {
        // Arrange
        String codigo = "CUP123";
        when(cuponRepository.findById(codigo))
            .thenReturn(Optional.empty());

        // Act
        String resultado = cuponService.eliminarCupon(codigo);

        // Assert
        assertEquals("El cupón con código CUP123 no existe.", resultado);
        verify(cuponRepository, never()).deleteById(codigo);
    }
}
