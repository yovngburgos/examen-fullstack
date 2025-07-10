//Service desarrollado por Iván Carrasco
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contenido;
import com.example.demo.model.Curso;
import com.example.demo.repository.ContenidoRepository;
import com.example.demo.repository.CursoRepository;

@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //Método para crear contenido
    
    public String crearContenido(Contenido contenido) {
        if (contenidoRepository.findByTitulo(contenido.getTitulo()).isPresent()) {
            return "El contenido ya existe";
        }
        contenidoRepository.save(contenido);
        return "Contenido creado correctamente";
    }
   
    //Método para listar contenido

    public List<Contenido> listarContenido() {
        return contenidoRepository.findAll();
    }

    //Método para asignar un contenido a un curso
    public String asignarContenidoACurso(String sigla, int id) {
        //Verificar si el curso y el contenido existen
        if (!contenidoRepository.existsById(id)) {
            return "Contenido no encontrado";
        }
        if (!cursoRepository.existsById(sigla)) {
            return "Curso no encontrado";
        }
        //Obtener el contenido y el curso
        Contenido contenido = contenidoRepository.findById(id).get();
        Curso curso = cursoRepository.findById(sigla).get();

        if (contenido.getCurso() != null) {
            return "El contenido ya está asignado a un curso";
        }
        //Asignar el contenido al curso
        contenido.setCurso(curso);
        //Guardar el contenido actualizado
        contenidoRepository.save(contenido);
        return "Contenido asignado al curso correctamente";
    }

    //Método para listar contenidos por curso
    public List<Contenido> listarContenidosPorCurso(String sigla) {
        if (!cursoRepository.existsById(sigla)) {
            return null; // Curso no encontrado
        }
        Curso curso = cursoRepository.findById(sigla).get();
        return curso.getContenidos(); // Retorna la lista de contenidos del curso
    }

    //Método para eliminar un contenido por ID
    public String eliminarContenido(int id) {
        if (!contenidoRepository.existsById(id)) {
            return "Contenido no encontrado";
        }
        contenidoRepository.deleteById(id);
        return "Contenido eliminado correctamente";
    }
}
