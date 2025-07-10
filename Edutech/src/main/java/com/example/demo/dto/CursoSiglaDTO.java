package com.example.demo.dto;

import java.util.List;

public class CursoSiglaDTO {
    private String sigla;
    private String nombre;
    private List<Alumno2DTO> alumnos;
    private List<ContenidoDTO> contenidos;

    public CursoSiglaDTO(String sigla, String nombre, List<Alumno2DTO> alumnos, List<ContenidoDTO> contenidos) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.alumnos = alumnos;
        this.contenidos = contenidos;
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

    public List<ContenidoDTO> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<ContenidoDTO> contenidos) {
        this.contenidos = contenidos;
    }

    
    

    
}
