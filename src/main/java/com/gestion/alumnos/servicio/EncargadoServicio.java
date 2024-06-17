package com.gestion.alumnos.servicio;

import com.gestion.alumnos.modelo.Encargado;
import com.gestion.alumnos.repositorio.EncargadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncargadoServicio {

    @Autowired
    private EncargadoRepositorio encargadoRepositorio;

    public List<Encargado> obtenerListaDeEncargados() {
        return encargadoRepositorio.findAll();
    }

    public Encargado registrarEncargado(Encargado encargado) {
        return encargadoRepositorio.save(encargado);
    }

    public Encargado actualizarEncargado(Long id, Encargado encargadoDetalles) {
        Encargado encargado = encargadoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Encargado no encontrado"));
        encargado.setNombre(encargadoDetalles.getNombre());
        encargado.setApellido(encargadoDetalles.getApellido());
        encargado.setCedula(encargadoDetalles.getCedula());
        encargado.setEmail(encargadoDetalles.getEmail());
        return encargadoRepositorio.save(encargado);
    }

    public void eliminarEncargado(Long id) {
        encargadoRepositorio.deleteById(id);
    }

    public Encargado obtenerEncargadoPorId(Long id) {
        return encargadoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Encargado no encontrado"));
    }
}
