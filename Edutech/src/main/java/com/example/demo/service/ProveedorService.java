//Joaquín Aguirre
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Proveedor;
import com.example.demo.repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    // Método para registrar un proveedor
    public String registrarProveedor(Proveedor proveedor) {
        if (proveedorRepository.existsById(proveedor.getRut())) {
            return "El proveedor ya existe";
        } else {
            proveedorRepository.save(proveedor);
            return "Proveedor registrado correctamente";
        }
    }

    // Método para listar todos los proveedores
    public Iterable<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    // Método para eliminar un proveedor
    public String eliminarProveedor(String rut) {
        if (proveedorRepository.existsById(rut)) {
            proveedorRepository.deleteById(rut);
            return "Proveedor eliminado correctamente";
        } else {
            return "El proveedor no existe";
        }
    }
    // Método para actualizar un proveedor
    public String actualizarProveedor(Proveedor proveedor) {
        if (proveedorRepository.existsById(proveedor.getRut())) {
            proveedorRepository.save(proveedor);
            return "Proveedor actualizado correctamente";
        } else {
            return "El proveedor no existe";
        }
    }
}
