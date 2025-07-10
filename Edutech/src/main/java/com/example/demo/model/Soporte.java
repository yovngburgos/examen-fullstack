package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Soporte {
    @Id
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String activo = "true"; // Por defecto, el soporte está activo
/*
    //Lista con las incidencias que ha atendido el soporte
    @OneToMany(mappedBy="soporte")
    @JsonBackReference("soporte-incidencias")
    private List<Incidencia> incidencias;
*/
    public Soporte() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.contrasena = "";
        this.activo = "true"; // Por defecto, el soporte está activo
    }

    //Getters y Setters

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
