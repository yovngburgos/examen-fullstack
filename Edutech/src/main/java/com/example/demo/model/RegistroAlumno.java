package com.example.demo.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class RegistroAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fechaInscripcion;
    private String estado = "pendiente"; // Puede ser "pendiente", "aprobada", "rechazada"

    //Registro se realiza por un alumno
    // Relación uno a uno entre Registro_alumno y Alumno
    // Cada registro pertenece a un único alumno y cada alumno tiene un único registro
    @OneToOne
    @JoinColumn(name = "alumno_rut")
    @JsonManagedReference("alumno-registro")
    private Alumno alumno;

    //Registro obtiene pagos
    // Relación uno a muchos entre Registro_alumno y Pago
    // Un registro puede tener múltiples pagos, pero un pago pertenece a un único registro
    @OneToMany(mappedBy = "registroAlumno")
    @JsonManagedReference("registro-pago")
    private List<Pago> pagos;

    // Constructor por defecto
    public RegistroAlumno() {
        this.fechaInscripcion = "";
        this.estado = "pendiente"; // Por defecto, la inscripción está pendiente
    }
    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }



    
 
    
}
