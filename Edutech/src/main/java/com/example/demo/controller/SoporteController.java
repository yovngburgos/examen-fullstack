package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.IncidenciaDTO;
import com.example.demo.model.Proveedor;
import com.example.demo.service.IncidenciaService;
import com.example.demo.service.ProveedorService;

@RestController
@RequestMapping("/soporte")
public class SoporteController {
    
    //Método para listar incidencias
    @Autowired
    private IncidenciaService incidenciaService;
    @GetMapping("/incidencias")
    public List<IncidenciaDTO> listarIncidencias() {
        return incidenciaService.listarIncidenciasDTO();
    }

    //Método para crear un proveedor
    @Autowired
    private ProveedorService proveedorService;
    @PostMapping("/crearProveedor")
    public String crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.registrarProveedor(proveedor);
    }

    //Método para listar proveedores
    @GetMapping("/proveedores")
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorService.listarProveedores();
    }

    //Método para eliminar un proveedor por rut
    @PostMapping("/proveedores/eliminar/{rut}")
    public String eliminarProveedor(@PathVariable String rut) {
        return proveedorService.eliminarProveedor(rut);
    }

    //Método para eliminar una incidencia por ID
    @PostMapping("/incidencias/eliminar/{id}")
    public String eliminarIncidencia(@PathVariable int id) {
        return incidenciaService.eliminarIncidencia(id);
    }
    

}
