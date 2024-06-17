package com.gestion.alumnos.controlador;

import com.gestion.alumnos.modelo.Curso;
import com.gestion.alumnos.servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping
    public List<Curso> obtenerListaDeCursos() {
        return cursoServicio.obtenerListaDeCursos();
    }

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoServicio.registrarCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso cursoDetalles) {
        Curso cursoActualizado = cursoServicio.actualizarCurso(id, cursoDetalles);
        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoServicio.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        Curso curso = cursoServicio.obtenerCursoPorId(id);
        return ResponseEntity.ok(curso);
    }
}
