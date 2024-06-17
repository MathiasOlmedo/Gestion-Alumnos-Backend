package com.gestion.alumnos.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long alumnoId;
    private Long encargadoId;
    private Long cursoId;

    private String alumnoNombre;
    private String alumnoApellido;
    private String encargadoNombre;
    private String encargadoApellido;
    private String cursoNombre;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    private String estado;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Long getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(Long encargadoId) {
        this.encargadoId = encargadoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
    }

    public String getAlumnoApellido() {
        return alumnoApellido;
    }

    public void setAlumnoApellido(String alumnoApellido) {
        this.alumnoApellido = alumnoApellido;
    }

    public String getEncargadoNombre() {
        return encargadoNombre;
    }

    public void setEncargadoNombre(String encargadoNombre) {
        this.encargadoNombre = encargadoNombre;
    }

    public String getEncargadoApellido() {
        return encargadoApellido;
    }

    public void setEncargadoApellido(String encargadoApellido) {
        this.encargadoApellido = encargadoApellido;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
