package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contenido;
import com.example.demo.model.Evaluacion;
import com.example.demo.service.ContenidoService;
import com.example.demo.service.EvaluacionService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    //Método para crear contenido

    @Autowired
    private ContenidoService contenidoService;
    @PostMapping("/contenido")
    public String crearContenido(@RequestBody Contenido contenido) {
        return contenidoService.crearContenido(contenido);
    }
    //Método para listar contenido

    @GetMapping("/contenido")
    public List<Contenido> listarContenido() {
        return contenidoService.listarContenido();
    }
    
    //Método para crear evaluaciones
    @Autowired
    private EvaluacionService evaluacionService;
    @PostMapping("/evaluacion")
    public String crearEvaluacion(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.crearEvaluacion(evaluacion);
    }
    //Método para listar evaluaciones
    
    @GetMapping("/evaluacion")
    public List<Evaluacion> listarEvaluacion() {
        return evaluacionService.listarEvaluacion();
    }

    //Método para listar contenidos por curso
    @GetMapping("/contenido/{sigla}")
    public List<Contenido> listarContenidosPorCurso(@PathVariable String sigla) {
        return contenidoService.listarContenidosPorCurso(sigla);
    }

    //Método para listar evaluaciones por curso
    @GetMapping("/evaluacion/{sigla}")
    public List<Evaluacion> listarEvaluacionesPorCurso(@PathVariable String sigla) {
        return evaluacionService.listarEvaluacionesPorCurso(sigla);
    }

    //Método para asignar un contenido a un curso
    @PostMapping("/contenido/{id}/asignar/{sigla}")
    public String asignarContenidoACurso(@PathVariable String sigla, @PathVariable int id) {
        return contenidoService.asignarContenidoACurso(sigla, id);
    }

    //Método para asignar una evaluación a un curso
    @PostMapping("/evaluacion/{id}/asignar/{sigla}")
    public String asignarEvaluacionACurso(@PathVariable String sigla, @PathVariable int id) {
        return evaluacionService.asignarEvaluacionACurso(sigla, id);
    }

    //Método para eliminar evaluación por ID
    @PostMapping("/evaluacion/eliminar/{id}")
    public String eliminarEvaluacion(@PathVariable int id) {
        return evaluacionService.eliminarEvaluacion(id);
    }

    //Método para eliminar contenido por ID
    @PostMapping("/contenido/eliminar/{id}")
    public String eliminarContenido(@PathVariable int id) {
        return contenidoService.eliminarContenido(id);
    }
}
