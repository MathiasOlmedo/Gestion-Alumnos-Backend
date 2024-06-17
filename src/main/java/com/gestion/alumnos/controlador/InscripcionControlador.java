package com.gestion.alumnos.controlador;

import com.gestion.alumnos.dto.InscripcionDTO;
import com.gestion.alumnos.modelo.Alumno;
import com.gestion.alumnos.modelo.Curso;
import com.gestion.alumnos.modelo.Encargado;
import com.gestion.alumnos.modelo.Inscripcion;
import com.gestion.alumnos.servicio.AlumnoServicio;
import com.gestion.alumnos.servicio.CursoServicio;
import com.gestion.alumnos.servicio.EncargadoServicio;
import com.gestion.alumnos.servicio.InscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v2/inscripciones")
public class InscripcionControlador {

    @Autowired
    private InscripcionServicio inscripcionServicio;

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private EncargadoServicio encargadoServicio;

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping
    public List<Inscripcion> obtenerListaDeInscripciones() {
        return inscripcionServicio.obtenerListaDeInscripciones();
    }

    @PostMapping
    public ResponseEntity<Inscripcion> createInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAlumnoId(inscripcionDTO.getAlumnoId());
        inscripcion.setEncargadoId(inscripcionDTO.getEncargadoId());
        inscripcion.setCursoId(inscripcionDTO.getCursoId());
    
        inscripcion.setEstado(inscripcionDTO.getEstado());

        Alumno alumno = alumnoServicio.obtenerAlumnoPorId(inscripcionDTO.getAlumnoId());
        Encargado encargado = encargadoServicio.obtenerEncargadoPorId(inscripcionDTO.getEncargadoId());
        Curso curso = cursoServicio.obtenerCursoPorId(inscripcionDTO.getCursoId());

        inscripcion.setAlumnoNombre(alumno.getNombre());
        inscripcion.setAlumnoApellido(alumno.getApellido());
        inscripcion.setEncargadoNombre(encargado.getNombre());
        inscripcion.setEncargadoApellido(encargado.getApellido());
        inscripcion.setCursoNombre(curso.getNombre());

        Inscripcion nuevaInscripcion = inscripcionServicio.saveInscripcion(inscripcion);
        return ResponseEntity.ok(nuevaInscripcion);
    }
}
