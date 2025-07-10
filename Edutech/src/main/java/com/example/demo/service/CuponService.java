//Service desarrollado por Benjamín Burgos
package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cupon;
import com.example.demo.repository.CuponRepository;

@Service
public class CuponService {
    @Autowired
    private CuponRepository cuponRepository;
    //Crear un cupón
    public String crearCupon(Cupon cupon) {
        if(cuponRepository.findById(cupon.getCodigo()).isPresent()) {
            return "El cupón con ID " + cupon.getCodigo() + " ya existe.";
        }
        cuponRepository.save(cupon);
        return "Cupón " + cupon.getCodigo()+" creado exitosamente.";
    }

    //Validar un cupón
    /*
    public String validarCupon(String codigo) {
        if(cuponRepository.findById(codigo).isPresent()) {
            Cupon cupon = cuponRepository.findById(codigo).get();
            if(cupon.getActivo().equals("true")) {
                return "El cupón " + cupon.getCodigo() + " es válido.";
            } else {
                return "El cupón " + cupon.getCodigo() + " no está activo.";
            }
        } else {
            return "El código con ID " + codigo + " no existe.";
        }
    }
    */
    public String validarCupon(String codigo) {
    Optional<Cupon> cuponOpt = cuponRepository.findById(codigo);
    if (cuponOpt.isPresent()) {
        Cupon cupon = cuponOpt.get();
        if ("true".equals(cupon.getActivo())) {
            return "El cupón " + cupon.getCodigo() + " es válido.";
        } else {
            return "El cupón " + cupon.getCodigo() + " no está activo.";
        }
    } else {
        return "El código con ID " + codigo + " no existe.";
    }
    }

    //Aplicar un cupón a un regisro de alumno devolviendo el descuento y restando su cantidad de usos, 
    //si la cantidad llega a 0 cambiar estado a "false"
    public String aplicarCupon(String codigo) {
        if(cuponRepository.findById(codigo).isPresent()) {
            Cupon cupon = cuponRepository.findById(codigo).get();
            if(cupon.getActivo().equals("true")) {
                if(cupon.getCantidadUsos() > 0) {
                    cupon.setCantidadUsos(cupon.getCantidadUsos() - 1);
                    if(cupon.getCantidadUsos() == 0) {
                        cupon.setActivo("false");
                    }
                    cuponRepository.save(cupon);
                    return "Cupón " + cupon.getCodigo() + " aplicado con éxito. Descuento: " + cupon.getDescuento() + "%";
                } else {
                    return "El cupón " + cupon.getCodigo() + " ya no tiene usos disponibles.";
                }
            } else {
                return "El cupón " + cupon.getCodigo() + " no está activo.";
            }
        } else {
            return "El cupón con código " + codigo + " no existe.";
        }
    }

    //Obtener un cupón por código
    public Cupon obtenerCuponPorCodigo(String codigo) {
        return cuponRepository.findById(codigo).get();
    }

    //Eliminar un cupón por ID
    public String eliminarCupon(String codigo) {
        if(cuponRepository.findById(codigo).isPresent()) {
            cuponRepository.deleteById(codigo);
            return "Cupón con código " + codigo + " eliminado exitosamente.";
        } else {
            return "El cupón con código " + codigo + " no existe.";
        }
    }
}