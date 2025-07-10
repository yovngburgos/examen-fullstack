package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pago;
import com.example.demo.model.RegistroAlumno;
import com.example.demo.repository.PagoRepository;
import com.example.demo.repository.RegistroAlumnoRepository;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    
    // Método para crear un pago asociado
    public String crearPago(Pago pago) {
        pagoRepository.save(pago);
        return "Pago creado correctamente";
    }

    @Autowired
    private RegistroAlumnoRepository registroAlumnoRepository;
    //Método para crear un pago asociado y asociarlo a un registro de alumno
    public String crearPagoConRegistro(Pago pago, int registroId) {
    Optional<RegistroAlumno> optionalRegistro = registroAlumnoRepository.findById(registroId);

    if (optionalRegistro.isPresent()) {
        RegistroAlumno registro = optionalRegistro.get();

        // Asociar el registro al pago
        pago.setRegistroAlumno(registro);

        // Cambiar estado del registro a "habilitado"
        registro.setEstado("habilitado");

        // Guardar el pago
        pagoRepository.save(pago);

        // Guardar el registro actualizado (opcional pero recomendable)
        registroAlumnoRepository.save(registro);

        return "Pago creado y asociado al registro con ID " + registroId;
    } else {
        return "Error: Registro con ID " + registroId + " no encontrado.";
    }
}
}