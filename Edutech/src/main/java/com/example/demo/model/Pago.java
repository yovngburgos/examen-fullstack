package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int monto;
    private String metodo_pago;
    private String ult_dig_tar;
    private String fecha_pago;
    
    //Varios pagos pueden ser asignados a un registro
    // Relación muchos a uno entre Pago y Registro
    // Cada pago pertenece a un único registro, pero un registro puede tener múltiples pagos

    @ManyToOne
    @JsonBackReference("registro-pago")
    private RegistroAlumno registroAlumno;

    //Varios pagos pueden ser asignados a un cupón
    // Relación muchos a uno entre Pago y Cupón
    // Cada pago puede aplicar un único cupón, pero un cupón puede ser aplicado en múltiples pagos
    @ManyToOne
    private Cupon cupon;

    // Constructor por defecto
    public Pago() {
        this.id = 0;
        this.monto = 0;
        this.metodo_pago = "";
        this.ult_dig_tar = "";
        this.fecha_pago = "";
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getUlt_dig_tar() {
        return ult_dig_tar;
    }

    public void setUlt_dig_tar(String ult_dig_tar) {
        this.ult_dig_tar = ult_dig_tar;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public RegistroAlumno getRegistroAlumno() {
        return registroAlumno;
    }

    public void setRegistroAlumno(RegistroAlumno registroAlumno) {
        this.registroAlumno = registroAlumno;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    

}
