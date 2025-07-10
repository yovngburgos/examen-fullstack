package com.example.demo.dto;

import java.util.List;

// CursoDTO Muestra solo la sigla y el nombre del curso
// y una lista de alumnos inscritos en el curso

public class CursoDTO {
    private String sigla;
    private String nombre;
    private List<Alumno2DTO> alumnos; // Usa AlumnoDTO, que no tiene contraseña

    public CursoDTO() {
        this.sigla = "";
        this.nombre = "";
        this.alumnos = null;
    }

    public CursoDTO(String sigla, String nombre, List<Alumno2DTO> alumnos) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.alumnos = alumnos;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno2DTO> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno2DTO> alumnos) {
        this.alumnos = alumnos;
    }

    
}