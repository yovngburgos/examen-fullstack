package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alumno;
import com.example.demo.model.Incidencia;
import com.example.demo.model.Pago;
import com.example.demo.model.RegistroAlumno;
import com.example.demo.model.Resena;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.IncidenciaService;
import com.example.demo.service.PagoService;
import com.example.demo.service.RegistroAlumnoService;
import com.example.demo.service.ResenaService;

@RestController
@RequestMapping("/Alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

// Método para registrar un nuevo alumno

    @PostMapping("/crearAlumno")
    public String almacenar(@RequestBody Alumno alumno) {
        return alumnoService.registrarAlumno(alumno);
    }

//Método para crear un registro de alumno ya existente
    @Autowired
    private RegistroAlumnoService registroAlumnoService;
    @PostMapping("/crearRegistroPorAlumno/{rut}")
    public String crearRegistroPorAlumno(@RequestBody RegistroAlumno registroAlumno, @PathVariable String rut) {
        return registroAlumnoService.crearRegistroPorAlumno(registroAlumno, rut);
    }

    //Método para realizar un pago asociado a un registro de alumno
    @Autowired
    private PagoService pagoService;
    @PostMapping("/crearPagoConRegistro/{registroId}")
    public String crearPagoConRegistro(@PathVariable int registroId, @RequestBody Pago pago) {
        return pagoService.crearPagoConRegistro(pago, registroId);
    }
    


// Método para asignar un curso a un alumno

    @PostMapping("/{rut}/inscribir/{sigla}")
    public String alumnoAsignarCurso(@PathVariable String rut, @PathVariable String sigla) {
        return alumnoService.alumnoAsignarCurso(rut, sigla);
    }

    @Autowired
    private IncidenciaService incidenciaService;
// Método para crear una incidencia con un alumno

    @PostMapping("/crearIncidenciaConAlumno/{rut}")
    public String crearIncidenciaConAlumno(@RequestBody Incidencia incidencia, @PathVariable String rut) {
        return incidenciaService.crearIncidencia(incidencia, rut);
    }   


    @Autowired
    private ResenaService resenaService;

// Método para dejar una reseña con alumno y curso

    @PostMapping("/dejarResena/{rut}/{sigla}")
    public String dejarResenaConAlumnoYCurso(@RequestBody Resena resena, @PathVariable String rut, @PathVariable String sigla) {
        return resenaService.dejarResena2(resena, rut, sigla);
    }



// Método para iniciar sesión de un alumno

    @PostMapping("/login/{rut}/{contrasena}")
    public String iniciarSesion(@PathVariable String rut, @PathVariable String contrasena) {
        return alumnoService.iniciarSesion(rut, contrasena);
    }
}
