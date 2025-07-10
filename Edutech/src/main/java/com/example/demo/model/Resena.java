package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comentario;
    private int calificacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    //Una reseña es escrita por un alumno
    // Relación muchos a uno entre Resena y Alumno
    // Cada reseña es escrita por un único alumno, pero un alumno puede escribir múltiples reseñas
    @ManyToOne
    private Alumno alumno;

    //Una reseña se realiza en un curso
    // Relación muchos a uno entre Resena y Curso
    // Cada reseña pertenece a un único curso, pero un curso puede tener múltiples reseñas
    @ManyToOne
    private Curso curso;

    // Constructor por defecto
    public Resena() {
        this.id = 0;
        this.comentario = "";
        this.calificacion = 0;
        this.fecha = Date.valueOf(java.time.LocalDate.now()); // Inicializa con la fecha actual
    }

    // Getters y Setters

    public Alumno getAlumno() {
        return alumno;
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    
}
