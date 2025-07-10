package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alumno;
import com.example.demo.model.GestorCursos;
import com.example.demo.model.RegistroAlumno;
import com.example.demo.dto.AlumnoDTO;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.GestorCursosService;
import com.example.demo.service.RegistroAlumnoService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AlumnoService alumnoService;

// Método para registrar un nuevo alumno

    @PostMapping("/alumnos")
    public String almacenar(@RequestBody Alumno alumno) {
        return alumnoService.registrarAlumno(alumno);
    }

// Método para listar todos los alumnos

    @GetMapping("/alumnos")
    public List<AlumnoDTO> listarAlumnos() {
        return alumnoService.listarAlumnos();
    }

// Método para registrar un nuevo Gestor de Cursos
    @Autowired
    private GestorCursosService gestorCursosService;
    @PostMapping("/gestores")
    public String almacenarGestorCursos(@RequestBody GestorCursos gestorCursos) {
        return gestorCursosService.registrarGestorCursos(gestorCursos);
    }

// Método para listar todos los Gestores de Cursos

    @GetMapping("/gestores")
    public List<GestorCursos> listargGestoresCursos() {
        return gestorCursosService.listarGestoresCursos();
    }

    //Método para listar todos los registros de alumnos
    @Autowired
    private RegistroAlumnoService registroAlumnoService;
    @GetMapping("/registros")
    public List<RegistroAlumno> listarRegistros() {
        return registroAlumnoService.listarRegistros();
    }
}
