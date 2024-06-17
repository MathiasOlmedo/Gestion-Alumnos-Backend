package com.gestion.alumnos.controlador;

import com.gestion.alumnos.modelo.Alumno;
import com.gestion.alumnos.servicio.AlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/alumnos")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping
    public List<Alumno> obtenerListaDeAlumnos() {
        return alumnoServicio.obtenerListaDeAlumnos();
    }

    @PostMapping
    public Alumno registrarAlumno(@RequestBody Alumno alumno) {
        return alumnoServicio.registrarAlumno(alumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id) {
        Alumno alumno = alumnoServicio.obtenerAlumnoPorId(id);
        return ResponseEntity.ok(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetalles) {
        Alumno alumnoActualizado = alumnoServicio.actualizarAlumno(id, alumnoDetalles);
        return ResponseEntity.ok(alumnoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        alumnoServicio.eliminarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
