package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String fecha;
    private String url;
    private String nota;
    private String corregida = "false"; // Por defecto, la evaluación no está corregida

    //Una evaluación pertenece a un curso
    // Relación muchos a uno entre Evaluacion y Curso
    // Cada evaluación pertenece a un único curso, pero un curso puede tener múltiples evaluaciones
    @ManyToOne
    private Curso curso;
/*
    // Una evaluación es creada por un instructor
    // Relación muchos a uno entre Evaluacion e Instructor
    // Cada evaluación es creada por un único instructor, pero un instructor puede crear múltiples evaluaciones
    @ManyToOne
    private Instructor instructor;
*/
    // Constructor por defecto
    public Evaluacion() {
        this.id = 0;
        this.titulo = "";
        this.descripcion = "";
        this.url = "";
    }
    // getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCorregida() {
        return corregida;
    }

    public void setCorregida(String corregida) {
        this.corregida = corregida;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }


}
