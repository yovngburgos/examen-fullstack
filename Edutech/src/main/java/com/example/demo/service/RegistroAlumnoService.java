//Joaquín Aguirre
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.RegistroAlumno;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.RegistroAlumnoRepository;

@Service
public class RegistroAlumnoService {
    @Autowired
    private RegistroAlumnoRepository registroAlumnoRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    //Crear un registro asociado a un alumno ya creado
    public String crearRegistroPorAlumno(RegistroAlumno registroAlumno, String rut) {
    // Buscar al alumno por rut
        Alumno alumno = alumnoRepository.findById(rut).orElse(null);

        if (alumno == null) {
            return "Error: Alumno con rut " + rut + " no encontrado.";
        }

        // Asociar el alumno al registro
        registroAlumno.setAlumno(alumno);

        // Guardar el registro
        registroAlumnoRepository.save(registroAlumno);

        return "Registro creado correctamente para el alumno con rut " + rut;
        }
    
    // Listar registros de alumnos
        public List<RegistroAlumno> listarRegistros() {
            return registroAlumnoRepository.findAll();
    }   

    //Crear un registro de alumno
    public String crearRegistro(RegistroAlumno registro) {
        if (registroAlumnoRepository.findById(registro.getId()).isPresent()) {
            return "El registro con ID " + registro.getId() + " ya existe.";
        }
        registroAlumnoRepository.save(registro);
        return "Registro de alumno creado exitosamente.";
    }

    //Aprobar un registro de alumno
    public String aprobarRegistro(int id) {
        if (registroAlumnoRepository.findById(id).isPresent()) {
            RegistroAlumno registro = registroAlumnoRepository.findById(id).get();
            registro.setEstado("aprobado");
            registroAlumnoRepository.save(registro);
            return "Registro de alumno con ID " + id + " aprobado exitosamente.";
        } else {
            return "El registro con ID " + id + " no existe.";
        }
    }
    //Rechazar un registro de alumno
    public String rechazarRegistro(int id) {
        if (registroAlumnoRepository.findById(id).isPresent()) {
            RegistroAlumno registro = registroAlumnoRepository.findById(id).get();
            registro.setEstado("rechazada");
            registroAlumnoRepository.save(registro);
            return "Registro de alumno con ID " + id + " rechazado exitosamente.";
        } else {
            return "El registro con ID " + id + " no existe.";
        }
    }

    //Obtener un registro de alumno por ID
    public RegistroAlumno obtenerRegistroPorId(int id) {
        return registroAlumnoRepository.findById(id).orElse(null);
    }

}
