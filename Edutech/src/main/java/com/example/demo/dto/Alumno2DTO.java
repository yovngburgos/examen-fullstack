package com.example.demo.dto;

//Alumno2DTO Muestra solo el rut, nombre y apellido del alumno
// y no la contraseña ni el mail

public class Alumno2DTO {
private String rut;
    private String nombre;
    private String apellido;

    public Alumno2DTO() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        }

    public Alumno2DTO(String rut, String nombre, String apellido) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }
   
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

}
