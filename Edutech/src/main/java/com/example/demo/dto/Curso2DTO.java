package com.example.demo.dto;

public class Curso2DTO {
    private String sigla;
    private String nombre;

    public Curso2DTO(String sigla, String nombre) {
        this.sigla = sigla;
        this.nombre = nombre;
    }

    // Getters y setters

    public String getSigla() {
        return sigla;
    }   
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}