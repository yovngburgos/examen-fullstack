//Service desarrollado por Benjamín Burgos
package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InstructorDTO;
import com.example.demo.dto.Curso2DTO;
import com.example.demo.model.Curso;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.InstructorRepository;

@Service
public class InstructorService {

    // Método para registrar un instructor
    @Autowired
    private InstructorRepository instructorRepository;

    public String registrarInstructor(Instructor instructor) {
        if (instructorRepository.findById(instructor.getRut()).isPresent()) {
            return "El instructor ya existe";
        }
        instructorRepository.save(instructor);
        return "Instructor registrado correctamente";
    }
    
    // Método para listar todos los instructores
    public List<InstructorDTO> listarInstructores() {
    return instructorRepository.findAll().stream()
        .map(instructor -> new InstructorDTO(
            instructor.getRut(),
            instructor.getNombre(),
            instructor.getApellido(),
            instructor.getEmail(),
            instructor.getEspecialidad(),
            instructor.getActivo(),
            instructor.getCursos().stream()
                .map(curso -> new Curso2DTO(
                    curso.getSigla(),
                    curso.getNombre()
                )).collect(Collectors.toList())
        )).collect(Collectors.toList());
}

        //Método para asignar un instructor a un curso
    @Autowired
    private CursoRepository cursoRepository;
    public String instructorAsignarCurso(String rut, String sigla) {
        // OBTENEMOS INSTRUCTOR Y CURSO
        if(!cursoRepository.existsById(sigla)) {
            return "El curso ingresado no fue encontrado";
        }

        if(!instructorRepository.existsById(rut)) {
            return "El instructor ingresado no fue encontrado";
        }

        Curso curso = cursoRepository.findById(sigla).get();
        Instructor instructor = instructorRepository.findById(rut).get();

        if(curso.getInstructor() != null) {
            return "El curso ya tiene un instructor asignado";
        } else {
            curso.setInstructor(instructor);
            cursoRepository.save(curso);
            return "Instructor asignado al curso correctamente";
        }
    }
    
}
