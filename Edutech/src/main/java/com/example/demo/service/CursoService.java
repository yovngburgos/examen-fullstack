//Service desarrollado por Iván Carrasco

package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Alumno2DTO;
import com.example.demo.dto.ContenidoDTO;
import com.example.demo.dto.CursoDTO;
import com.example.demo.dto.CursoSiglaDTO;
import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    
// Método para registrar un curso

    public String registrarCurso(Curso curso) {
        if (cursoRepository.findById(curso.getSigla()).isPresent()) {
            return "El curso ya existe";
        }
        cursoRepository.save(curso);
        return "Curso registrado correctamente";
    }


//Método para listar cursos con DTO

    public List<CursoDTO> listarCursos() {
    List<Curso> cursos = cursoRepository.findAll();
    return cursos.stream()
            .map(curso -> new CursoDTO(
                    curso.getSigla(),
                    curso.getNombre(),
                    curso.getAlumnos().stream()
                        .map(alumno -> new Alumno2DTO(
                            alumno.getRut(),
                            alumno.getNombre(),
                            alumno.getApellido()
                        )).collect(Collectors.toList())
                        )).collect(Collectors.toList());
}
/*
// Método para listar cursos por SIGLA
    public CursoDTO listarCursoPorSigla(String sigla) {
        return cursoRepository.findById(sigla)
                .map(curso -> new CursoDTO(
                        curso.getSigla(),
                        curso.getNombre(),
                        curso.getAlumnos().stream()
                            .map(alumno -> new Alumno2DTO(
                                alumno.getRut(),
                                alumno.getNombre(),
                                alumno.getApellido()
                            )).collect(Collectors.toList()),
                        )).orElseThrow(() -> new RuntimeException("Curso con sigla '" + sigla + "' no fue encontrado."));
    }
*/
//Método para listar cursos por SIGLA con contenidos (CursoSiglaDTO)

    public CursoSiglaDTO listarCursoPorSiglaConContenidos(String sigla) {
        return cursoRepository.findById(sigla)
                .map(curso -> new CursoSiglaDTO(
                        curso.getSigla(),
                        curso.getNombre(),
                        curso.getAlumnos().stream()
                            .map(alumno -> new Alumno2DTO(
                                alumno.getRut(),
                                alumno.getNombre(),
                                alumno.getApellido()
                            )).collect(Collectors.toList()),
                    curso.getContenidos().stream()
                        .map(contenido -> new ContenidoDTO(
                            contenido.getId(),
                            contenido.getTitulo(),
                            contenido.getDescripcion(),
                            contenido.getUrl(),
                            contenido.getFecha_subida()
                        )).collect(Collectors.toList())
                    )).orElseThrow(() -> new RuntimeException("Curso con sigla '" + sigla + "' no fue encontrado."));
    }

// Método para eliminar un curso por SIGLA
    public String eliminarCurso(String sigla) {
        if (cursoRepository.findById(sigla).isPresent()) {
            cursoRepository.deleteById(sigla);
            return "Curso eliminado correctamente";
        } else {
            return "El curso no existe";
        }
    }
// Método para actualizar un curso por sigla
    public String actualizarCurso(Curso curso, String sigla) {
        if (cursoRepository.findById(curso.getSigla()).isPresent()) {
            cursoRepository.save(curso);
            return "Curso actualizado correctamente";
        } else {
            return "El curso no existe";
        }
    }

}
