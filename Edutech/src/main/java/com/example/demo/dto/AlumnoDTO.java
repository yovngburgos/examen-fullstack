package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Curso;

// AlumnoDTO Muestra solo el rut, nombre y apellido del alumno
// y no la contraseña ni el mail
// Además, incluye una lista de cursos a los que está inscrito

public class AlumnoDTO {
private String rut;
    private String nombre;
    private String apellido;
    private List<Curso> cursos; // Relación con Curso

    public AlumnoDTO() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.cursos = null;
        }

    public AlumnoDTO(String rut, String nombre, String apellido, List<Curso> cursos) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cursos = cursos;
    }

    public List<Curso> getCursos() {
        return cursos;
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

}
