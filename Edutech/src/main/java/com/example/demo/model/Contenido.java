package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private String url;
    private String fecha_subida;
    private String tipo_contenido;
    private String aprobado = "false"; // Por defecto, el contenido no está aprobado
    
    //El contenido pertenece a un curso
    // Relación muchos a uno entre Contenido y Curso
    // Cada contenido pertenece a un único curso, pero un curso puede tener múltiples contenidos
    @ManyToOne
    private Curso curso;
/* 
    //El contenido es creado por un instructor
    // Relación muchos a uno entre Contenido e Instructor
    // Cada contenido es creado por un único instructor, pero un instructor puede crear múltiples contenidos
    @ManyToOne
    private Instructor instructor;
    
*/
    // Constructor por defecto
    public Contenido() {
        this.titulo = "";
        this.descripcion = "";
        this.url = "";
        this.fecha_subida = "";
        this.tipo_contenido = "";
        this.aprobado = "false"; // Por defecto, el contenido no está aprobado
    }

    // Getters y Setters


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(String fecha_subida) {
        this.fecha_subida = fecha_subida;
    }

    public String getTipo_contenido() {
        return tipo_contenido;
    }

    public void setTipo_contenido(String tipo_contenido) {
        this.tipo_contenido = tipo_contenido;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    

}
