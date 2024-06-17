package com.gestion.alumnos.servicio;

import com.gestion.alumnos.modelo.Curso;
import com.gestion.alumnos.repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServicio {
    @Autowired
    private CursoRepositorio cursoRepositorio;

    public List<Curso> obtenerListaDeCursos() {
        return cursoRepositorio.findAll();
    }

    public Curso registrarCurso(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    public Curso actualizarCurso(Long id, Curso cursoDetalles) {
        Curso curso = cursoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setNombre(cursoDetalles.getNombre());
        return cursoRepositorio.save(curso);
    }

    public void eliminarCurso(Long id) {
        cursoRepositorio.deleteById(id);
    }

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}
