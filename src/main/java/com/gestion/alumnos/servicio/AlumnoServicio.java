package com.gestion.alumnos.servicio;

import com.gestion.alumnos.modelo.Alumno;
import com.gestion.alumnos.repositorio.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    public List<Alumno> obtenerListaDeAlumnos() {
        return alumnoRepositorio.findAll();
    }

    public Alumno registrarAlumno(Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoDetalles) {
        Alumno alumno = alumnoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        alumno.setNombre(alumnoDetalles.getNombre());
        alumno.setApellido(alumnoDetalles.getApellido());
        alumno.setEmail(alumnoDetalles.getEmail());
        return alumnoRepositorio.save(alumno);
    }

    public void eliminarAlumno(Long id) {
        alumnoRepositorio.deleteById(id);
    }

    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }
}
