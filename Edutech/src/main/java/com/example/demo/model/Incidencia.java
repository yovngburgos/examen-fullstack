package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private String fecha;
    private String estado;
    private String tipo;
    private String respuesta;
/*
    // Soporte atiende la incidencia
    // Relación muchos a uno entre Incidencia y Soporte
    // Cada incidencia es atendida por un único soporte, pero un soporte puede atender múltiples incidencias
    @ManyToOne
    @JoinColumn(name = "soporte_id")
    @JsonManagedReference("soporte-incidencias")
    private Soporte soporte; 
*/
    //Alumno reporta incidencia
    // Relación muchos a uno entre Incidencia y Alumno
    // Cada incidencia es reportada por un único alumno, pero un alumno puede reportar múltiples incidencias
    @ManyToOne
    private Alumno alumno;

    // Constructor por defecto
    public Incidencia() {
        this.id = 0;
        this.descripcion = "";
        this.fecha = "";
        this.estado = "";
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }


    
    
}
