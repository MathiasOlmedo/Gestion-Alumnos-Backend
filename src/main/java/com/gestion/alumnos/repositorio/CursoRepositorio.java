package com.gestion.alumnos.repositorio;

import com.gestion.alumnos.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}
