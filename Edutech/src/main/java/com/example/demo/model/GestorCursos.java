package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GestorCursos {
    @Id
    private String rut;;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String activo = "true"; // Por defecto, el gestor de cursos está activo
    
    public GestorCursos() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.contrasena = "";
        this.activo = "true"; // Por defecto, el gestor de cursos está activo
    }
/*
    //GestorCursos puede gestionar cursos
    // Relación uno a muchos entre GestorCursos y Curso
    // Cada gestor de cursos puede gestionar múltiples cursos
    @OneToMany(mappedBy="gestorCursos")
    @JsonBackReference
    private List<Curso> cursos; 
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

}