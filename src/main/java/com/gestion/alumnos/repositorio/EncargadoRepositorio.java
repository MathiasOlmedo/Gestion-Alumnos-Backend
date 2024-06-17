package com.gestion.alumnos.repositorio;

import com.gestion.alumnos.modelo.Encargado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoRepositorio extends JpaRepository<Encargado, Long> {
}

