package com.gestion.alumnos.repositorio;

import com.gestion.alumnos.modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, Long> {
}
