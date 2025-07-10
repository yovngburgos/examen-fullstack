
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EstadisticasSistemaDTO;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.ContenidoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EvaluacionRepository;
import com.example.demo.repository.IncidenciaRepository;
import com.example.demo.repository.PagoRepository;
import com.example.demo.repository.RegistroAlumnoRepository;
import com.example.demo.repository.ResenaRepository;


@Service
public class AdminService {
     
    //Métodos para monitorizar el sistema
    
    @Autowired private AlumnoRepository alumnoRepository;
    @Autowired private CursoRepository cursoRepository;
    @Autowired private IncidenciaRepository incidenciaRepository;
    @Autowired private RegistroAlumnoRepository registroAlumnoRepository;
    @Autowired private PagoRepository pagoRepository;
    @Autowired private ResenaRepository resenaRepository;
    @Autowired private ContenidoRepository contenidoRepository;
    @Autowired private EvaluacionRepository evaluacionRepository;

    public EstadisticasSistemaDTO monitorizarSistema() {
        EstadisticasSistemaDTO dto = new EstadisticasSistemaDTO();
        dto.setTotalAlumnos(alumnoRepository.count());
        dto.setTotalCursos(cursoRepository.count());
        dto.setTotalIncidencias(incidenciaRepository.count());
        dto.setTotalRegistros(registroAlumnoRepository.count());
        dto.setTotalPagos(pagoRepository.count());
        dto.setTotalReseñas(resenaRepository.count());
        dto.setTotalContenidos(contenidoRepository.count());
        dto.setTotalEvaluaciones(evaluacionRepository.count());
        return dto;
    }

}
