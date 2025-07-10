package com.example.demo.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Instructor {
    @Id
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String especialidad;
    private String activo = "true"; // Por defecto, el instructor está activo

    public Instructor() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.contrasena = "";
        this.especialidad = "";
        this.activo = "true"; // Por defecto, el instructor está activo
    }

    //El instructor imparte cursos
    // Relación uno a muchos entre Instructor y Curso
    // Cada instructor puede impartir múltiples cursos
    @OneToMany(mappedBy="instructor")
    @JsonIgnore
    private List<Curso> cursos;
/*
    //El instructor crea contenidos
    // Relación uno a muchos entre Instructor y Contenido
    // Cada instructor puede crear múltiples contenidos
    @OneToMany(mappedBy="instructor")
    @JsonBackReference
    private List<Contenido> contenidos;

    //El instructor crea evaluaciones
    // Relación uno a muchos entre Instructor y Evaluacion
    // Cada instructor puede crear múltiples evaluaciones
    @OneToMany(mappedBy="instructor")
    @JsonBackReference
    private List<Evaluacion> evaluaciones;
*/
    // Getters y Setters
    
    
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public List<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }


    
}
