package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {
    @Id
    private String sigla;
    private String nombre;

    //El curso tiene alumnos
    // Relación muchos a muchos entre Curso y Alumno
    // Cada curso puede tener múltiples alumnos y cada alumno puede inscribirse en múltiples cursos
    @ManyToMany(mappedBy="cursos")
    @JsonBackReference("alumno-curso")
    private List<Alumno> alumnos;

    //El curso tiene contenidos
    // Relación uno a muchos entre curso y Contenido
    // Cada curso puede tener múltiples contenidos, pero un contenido pertenece a un único curso
    @OneToMany(mappedBy="curso")
    @JsonIgnore
    private List<Contenido> contenidos;

    //EL curso tiene evaluaciones
    // Relación uno a muchos entre curso y Evaluacion
    // Cada curso puede tener múltiples evaluaciones, pero una evaluación pertenece a un único curso
    @OneToMany(mappedBy="curso")
    @JsonIgnore
    private List<Evaluacion> evaluaciones;
      
    //EL curso tiene reseñas
    // Relación uno a muchos entre curso y Resena
    // Cada curso puede tener múltiples reseñas, pero una reseña pertenece a un único curso
    @OneToMany(mappedBy="curso")
    @JsonIgnore
    private List<Resena> resenas;
/* 
    //El curso es gestionado por un gestor de cursos
    // Relación muchos a uno entre Curso y GestorCursos
    // Cada curso es gestionado por un único gestor de cursos, pero un gestor de cursos puede gestionar múltiples cursos
    @ManyToOne
    private GestorCursos gestorCursos;
*/
    // Curso es impartido por un instructor
    // Relación muchos a uno entre Curso e Instructor
    // Cada curso es impartido por un único instructor, pero un instructor puede impartir múltiples cursos
    @ManyToOne
    private Instructor instructor;

    // Constructor por defecto
    public Curso() {
        this.sigla = "";
        this.nombre =  "";
    }

    // Getters y Setters

    public Instructor getInstructor() {
        return instructor;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    public List<Contenido> getContenidos() {
        return contenidos;
    }
    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
    public List<Resena> getResenas() {
        return resenas;
    }
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
    
    
}

