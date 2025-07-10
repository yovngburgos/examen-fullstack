//Desarrollado por Joaquín Aguirre
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GestorCursos;

import com.example.demo.repository.GestorCursosRepository;


@Service
public class GestorCursosService {
    
// Método para crear un gestor de cursos
    @Autowired
    private GestorCursosRepository gestorCursosRepository;

    public String registrarGestorCursos(GestorCursos gestorCursos) {
        if (gestorCursosRepository.findById(gestorCursos.getRut()).isPresent()) {
            return "El instructor ya existe";
        }
        gestorCursosRepository.save(gestorCursos);
        return "Instructor registrado correctamente";
    }
// Método para listar gestores de cursos
    public List<GestorCursos> listarGestoresCursos() {
        return gestorCursosRepository.findAll();
    }
    
}
