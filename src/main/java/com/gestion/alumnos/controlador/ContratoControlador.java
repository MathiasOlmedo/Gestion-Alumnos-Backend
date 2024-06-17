package com.gestion.alumnos.controlador;

import com.gestion.alumnos.modelo.Contrato;
import com.gestion.alumnos.modelo.Inscripcion;
import com.gestion.alumnos.servicio.ContratoServicio;
import com.gestion.alumnos.servicio.InscripcionServicio;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v2/contratos")
public class ContratoControlador {

    @Autowired
    private ContratoServicio contratoServicio;

    @Autowired
    private InscripcionServicio inscripcionServicio;

    @PostMapping("/generar")
    public ResponseEntity<Contrato> generarContrato(@RequestParam Long inscripcionId) {
        Inscripcion inscripcion = inscripcionServicio.obtenerInscripcionPorId(inscripcionId);
        if (inscripcion == null) {
            return ResponseEntity.notFound().build();
        }
        Contrato contrato = contratoServicio.generarContrato(inscripcion);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerContrato(@PathVariable Long id) {
        Contrato contrato = contratoServicio.obtenerContratoPorId(id);
        if (contrato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrato);
    }

    @GetMapping("/descargar/{id}")
    public ResponseEntity<byte[]> descargarContrato(@PathVariable Long id) {
        Contrato contrato = contratoServicio.obtenerContratoPorId(id);
        if (contrato == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        byte[] pdfContenido;
        try {
            pdfContenido = contratoServicio.generarPdfContrato(contrato);
        } catch (DocumentException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contrato_" + id + ".pdf");
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfContenido, headers, HttpStatus.OK);
    }
}
