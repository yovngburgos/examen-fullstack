package com.example.demo.dto;

import java.sql.Date;

public class ResenaDTO {
    private int id;
    private String comentario;
    private int calificacion;
    private Date fecha;
    private Alumno2DTO alumno;
    private Curso2DTO curso;

    public ResenaDTO(int id, String comentario, int calificacion, Date fecha, Alumno2DTO alumno, Curso2DTO curso) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.alumno = alumno;
        this.curso = curso;
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

    public Alumno2DTO getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno2DTO alumno) {
        this.alumno = alumno;
    }

    public Curso2DTO getCurso() {
        return curso;
    }
    public void setCurso(Curso2DTO curso) {
        this.curso = curso;
    }
}
