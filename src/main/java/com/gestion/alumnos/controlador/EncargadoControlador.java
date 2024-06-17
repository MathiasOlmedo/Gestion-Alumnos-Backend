package com.gestion.alumnos.controlador;

import com.gestion.alumnos.modelo.Encargado;
import com.gestion.alumnos.servicio.EncargadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/encargados")
public class EncargadoControlador {

    @Autowired
    private EncargadoServicio encargadoServicio;

    @GetMapping
    public List<Encargado> obtenerListaDeEncargados() {
        return encargadoServicio.obtenerListaDeEncargados();
    }

    @PostMapping
    public Encargado registrarEncargado(@RequestBody Encargado encargado) {
        return encargadoServicio.registrarEncargado(encargado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encargado> obtenerEncargadoPorId(@PathVariable Long id) {
        Encargado encargado = encargadoServicio.obtenerEncargadoPorId(id);
        return ResponseEntity.ok(encargado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encargado> actualizarEncargado(@PathVariable Long id, @RequestBody Encargado encargadoDetalles) {
        Encargado encargadoActualizado = encargadoServicio.actualizarEncargado(id, encargadoDetalles);
        return ResponseEntity.ok(encargadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEncargado(@PathVariable Long id) {
        encargadoServicio.eliminarEncargado(id);
        return ResponseEntity.noContent().build();
    }
}
