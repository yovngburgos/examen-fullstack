package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Alumno {
    @Id
    private String rut;
    private String mail;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String fecha_nac;
    private String activo = "true"; // Por defecto, el alumno está activo

//Alumno se inscribe en cursos
// Relación muchos a muchos entre Alumno y Curso
// Cada alumno puede inscribirse en múltiples cursos y cada curso puede tener múltiples alumnos
    @ManyToMany
    @JoinTable(
        name= "alumno_curso",
        joinColumns= @JoinColumn(name= "alumno_rut"),
        inverseJoinColumns= @JoinColumn(name= "curso_sigla")
    )
    
    @JsonBackReference("alumno-curso")
    private List<Curso> cursos;

//Alumno reporta incidencia
// Relación uno a muchos entre Alumno e Incidencia
// Cada alumno puede tener múltiples incidencias
    @OneToMany(mappedBy="alumno")
    @JsonIgnore
    private List<Incidencia> incidencias;

//Alumno deja una reseña
// Relación uno a muchos entre Alumno y Resena
// Cada alumno puede dejar múltiples reseñas
    @OneToMany(mappedBy="alumno")
    @JsonIgnore
    private List<Resena> resenas;

    //Un alumno se registra una única vez
    // Relación uno a uno entre Alumno y RegistroAlumno
    // Cada alumno tiene un único registro de inscripción
    @OneToOne(mappedBy = "alumno")
    @JsonBackReference("alumno-registro")
    private RegistroAlumno registro;

    public Alumno() {
        this.rut = "";
        this.mail = "";
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.contrasena = "";
        this.fecha_nac = "";
        this.activo = "true"; // Por defecto, el alumno está activo
    }

    // Getters y Setters
    public List<Resena> getResenas() {
        return resenas;
    }
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
    
    public List<Incidencia> getIncidencias() {
        return incidencias;
    }
    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
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
 
    public RegistroAlumno getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroAlumno registro) {
        this.registro = registro;
    }

    
    


}
