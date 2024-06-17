package com.gestion.alumnos.servicio;

import com.gestion.alumnos.modelo.Contrato;
import com.gestion.alumnos.modelo.Inscripcion;
import com.gestion.alumnos.repositorio.ContratoRepositorio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ContratoServicio {

    @Autowired
    private ContratoRepositorio contratoRepositorio;

    public Contrato generarContrato(Inscripcion inscripcion) {
        Contrato contrato = new Contrato();
        contrato.setInscripcion(inscripcion);
        contrato.setContenido("Con este presente legal Apruebo la Inscripción del Alumno antes mencionado y le doy la bienvenida a Nuestra Institución.");
        return contratoRepositorio.save(contrato);
    }

    public Contrato obtenerContratoPorId(Long id) {
        Optional<Contrato> contrato = contratoRepositorio.findById(id);
        return contrato.orElse(null);
    }

    public byte[] generarPdfContrato(Contrato contrato) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        // Encabezado
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph("Contrato de Inscripción", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); // Línea en blanco

        // Información de la inscripción
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        // Celda de encabezado
        table.addCell(new PdfPCell(new Phrase("Alumno:", fontHeader)));
        table.addCell(new PdfPCell(new Phrase(contrato.getInscripcion().getAlumnoNombre() + " " + contrato.getInscripcion().getAlumnoApellido())));

        table.addCell(new PdfPCell(new Phrase("Encargado:", fontHeader)));
        table.addCell(new PdfPCell(new Phrase(contrato.getInscripcion().getEncargadoNombre() + " " + contrato.getInscripcion().getEncargadoApellido())));

        table.addCell(new PdfPCell(new Phrase("Curso:", fontHeader)));
        table.addCell(new PdfPCell(new Phrase(contrato.getInscripcion().getCursoNombre())));

        table.addCell(new PdfPCell(new Phrase("Fecha de Inscripción:", fontHeader)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        table.addCell(new PdfPCell(new Phrase(contrato.getInscripcion().getFechaInscripcion().format(formatter))));

        table.addCell(new PdfPCell(new Phrase("Estado de Pago:", fontHeader)));
        table.addCell(new PdfPCell(new Phrase(contrato.getInscripcion().getEstado())));

        document.add(table);

        // Contenido del contrato
        Font fontContent = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph content = new Paragraph("Con este presente legal Apruebo la Inscripción del Alumno antes mencionado y le doy la bienvenida a Nuestra Institución.", fontContent);
        content.setSpacingBefore(20);
        content.setSpacingAfter(20);
        document.add(content);

        // Firma
        document.add(new Paragraph(" ")); // Línea en blanco
        Paragraph firma = new Paragraph("Firma del Encargado: _______________________", fontContent);
        firma.setSpacingBefore(50);
        document.add(firma);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
