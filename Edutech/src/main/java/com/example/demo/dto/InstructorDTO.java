package com.example.demo.dto;

import java.util.List;

public class InstructorDTO {
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;
    private String activo;
    private List<Curso2DTO> cursos;

    public InstructorDTO(String rut, String nombre, String apellido, String email, 
    String especialidad, String activo, List<Curso2DTO> cursos) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.especialidad = especialidad;
        this.activo = activo;
        this.cursos = cursos;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<Curso2DTO> getCursos() {
        return cursos;
    }
    public void setCursos(List<Curso2DTO> cursos) {
        this.cursos = cursos;
    }

    
}
