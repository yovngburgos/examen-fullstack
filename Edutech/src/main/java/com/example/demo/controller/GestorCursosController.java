package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CursoDTO;
import com.example.demo.dto.CursoSiglaDTO;
import com.example.demo.dto.InstructorDTO;
import com.example.demo.dto.ResenaDTO;
import com.example.demo.model.Curso;
import com.example.demo.model.Evaluacion;
import com.example.demo.model.Instructor;
import com.example.demo.model.Resena;
import com.example.demo.service.CursoService;
import com.example.demo.service.EvaluacionService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.ResenaService;

@RestController
@RequestMapping("/gestorCursos")
public class GestorCursosController {

//Método para almacenar un instructor
    @Autowired
    private InstructorService instructorService;
    @PostMapping("/instructores")
    public String almacenarInstructor(@RequestBody Instructor instructor) {
        return instructorService.registrarInstructor(instructor);
    }
    // Método para asignar un instructor a un curso
    @PostMapping("/{rut}/asignar/{sigla}")
    public String instructorAsignarCurso(@PathVariable String rut, @PathVariable String sigla) {
        return instructorService.instructorAsignarCurso(rut, sigla);
    }

    // Método para listar instructores

    @GetMapping("/instructores")
    public List<InstructorDTO> listarInstructores() {
        return instructorService.listarInstructores();
    }


    // Método para almacenar un curso
    @Autowired
    private CursoService cursoService;
    @PostMapping("/cursos")
    public String almacenarCurso(@RequestBody Curso curso) {
        return cursoService.registrarCurso(curso);
    }
// Método para listar cursos

    @GetMapping("/cursos")
    public List<CursoDTO> listarCursos() {
        return cursoService.listarCursos();
    }

// Método para listar curso por sigla
    @GetMapping("/cursos/{sigla}")
    public CursoSiglaDTO listarCursoPorSigla(@PathVariable String sigla) {
        return cursoService.listarCursoPorSiglaConContenidos(sigla);
    }
//Método para actualizar un curso por Sigla
    @PostMapping("/cursos/actualizar/{sigla}")
    public String actualizarCurso(@RequestBody Curso curso, String sigla) {
        return cursoService.actualizarCurso(curso, sigla);
    }

//Método para listar todas las reseñas
    @Autowired
    private ResenaService resenaService;
    @GetMapping("/resenas")
    public List<ResenaDTO> listarResenasDTO() {
        return resenaService.listarResenasDTO();
    }

// Método para listar reseñas por curso
    @GetMapping("/resenas/{sigla}")
    public List<Resena> listarResenasPorCurso(@PathVariable String sigla) {
        return resenaService.listarResenasPorCurso(sigla);
    }

// Método para actualizar una evaluación por ID
    @Autowired
    private EvaluacionService evaluacionService;
    @PutMapping("/evaluacion/actualizar/{id}")
    public ResponseEntity<Evaluacion> actualizarEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion evaluacionActualizada = evaluacionService.actualizarEvaluacion(id, evaluacion);
            return ResponseEntity.ok(evaluacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//Método para eliminar reseña por ID
    @PostMapping("/resenas/eliminar/{id}")
    public String eliminarResena(@PathVariable int id) {
        return resenaService.eliminarResena(id);
    }

    //Método para eliminar curso por sigla //No me funcionó por estar relacionada... revisar
    @PostMapping("/cursos/eliminar/{sigla}")
    public String eliminarCurso(@PathVariable String sigla) {
        return cursoService.eliminarCurso(sigla);
    }

}

    
