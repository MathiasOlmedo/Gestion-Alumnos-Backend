package com.gestion.alumnos.servicio;

import com.gestion.alumnos.modelo.Inscripcion;
import com.gestion.alumnos.repositorio.InscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServicio {

    @Autowired
    private InscripcionRepositorio inscripcionRepositorio;

    public List<Inscripcion> obtenerListaDeInscripciones() {
        return inscripcionRepositorio.findAll();
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepositorio.save(inscripcion);
    }

    public Inscripcion obtenerInscripcionPorId(Long id) {
        Optional<Inscripcion> inscripcion = inscripcionRepositorio.findById(id);
        return inscripcion.orElse(null);
    }
}
