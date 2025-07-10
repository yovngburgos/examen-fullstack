//Desarrollado por Joaquín Aguirre
package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contenido;
import com.example.demo.model.GestorCursos;
import com.example.demo.repository.ContenidoRepository;
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

//Método para aprobar contenido (cambiar campo de contenido aprobado a "true")
    @Autowired
    private ContenidoRepository contenidoRepository;
    public String aprobarContenido(int contenidoId) {
        Optional<Contenido> contenidoOptional = contenidoRepository.findById(contenidoId);
        if (contenidoOptional.isPresent()) {
            Contenido contenido = contenidoOptional.get();
            contenido.setAprobado("true"); 
            contenidoRepository.save(contenido);
            return "Contenido aprobado correctamente";
        }
        return "Contenido no encontrado";
    }
    
}
