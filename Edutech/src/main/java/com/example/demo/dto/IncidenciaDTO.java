package com.example.demo.dto;

public class IncidenciaDTO {
    private int id;
    private String descripcion;
    private String estado;
    private String fecha;
    private Alumno2DTO alumno;

    public IncidenciaDTO(int id, String descripcion, String estado, String fecha, Alumno2DTO alumno) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Alumno2DTO getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno2DTO alumno) {
        this.alumno = alumno;
    }

    
}
