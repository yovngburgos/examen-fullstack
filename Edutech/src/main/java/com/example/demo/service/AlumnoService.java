//Service desarrollado por Iván Carrasco
package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AlumnoDTO;
import com.example.demo.model.Alumno;
import com.example.demo.model.Curso;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.CursoRepository;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //Método para asignar un curso a un alumno

    public String alumnoAsignarCurso(String rut, String sigla) {
        // OBTENEMOS ALUMNO Y CURSO
        if(!cursoRepository.existsById(sigla)) {
            return "El curso ingresado no fue encontrado";
        }

        if(!alumnoRepository.existsById(rut)) {
            return "El alumno ingresado no fue encontrado";
        }

        Curso curso = cursoRepository.findById(sigla).get();
        Alumno alumno = alumnoRepository.findById(rut).get();

        if(alumno.getCursos().contains(curso)) {
            return "El alumno ya se encuentra inscrito en el curso";
        } else {
            alumno.getCursos().add(curso);
            alumnoRepository.save(alumno);
            return "Alumno inscrito al curso correctamente";
        }
    }

// Método para registrar un alumno

    public String registrarAlumno(Alumno alumno) {
        if (alumnoRepository.findById(alumno.getRut()).isPresent()) {
            return "El alumno con RUT " + alumno.getRut() + " ya existe.";
        }

        alumnoRepository.save(alumno);
        return "Alumno registrado exitosamente.";
    }
//Método para listar alumnos con respectivo DTO que oculta contraseña
// y devuelve solo los datos necesarios
    public List<AlumnoDTO> listarAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                .map(alumno -> new AlumnoDTO(
                        alumno.getRut(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getCursos()
                )).collect(Collectors.toList());
    }

//Método para iniciar sesión de un administrador

    public String iniciarSesion(String rut, String contrasena) {
        Alumno alumno = alumnoRepository.findById(rut).orElse(null);

        if (alumno != null && alumno.getContrasena().equals(contrasena)) {
            return "Sesión iniciada correctamente";
        }
        return "Usuario o contraseña incorrectos";
    }

}
