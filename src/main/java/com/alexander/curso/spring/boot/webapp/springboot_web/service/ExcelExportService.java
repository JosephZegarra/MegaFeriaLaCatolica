package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelExportService {

    /**
     * Exporta movimientos (Ingresos/Egresos) a Excel
     * Encabezados: Socio que registró, Tipo, Descripción, Monto, Fecha
     */
    public byte[] exportMovimientos(List<MovimientoEntity> movimientos) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Ingresos y Egresos");

            // Estilo para encabezados (fondo verde, texto blanco, negrita)
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Crear fila de encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = { "Socio que registró", "Tipo", "Descripción", "Monto", "Fecha" };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Formato de fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Llenar datos
            int rowNum = 1;
            for (MovimientoEntity mov : movimientos) {
                Row row = sheet.createRow(rowNum++);

                // Socio que registró (nombre completo)
                String nombreCompleto = mov.getSocio().getNombresocio() + " " +
                        mov.getSocio().getApellidopaternosocio();
                if (mov.getSocio().getApellidomaternosocio() != null) {
                    nombreCompleto += " " + mov.getSocio().getApellidomaternosocio();
                }
                row.createCell(0).setCellValue(nombreCompleto);

                // Tipo
                row.createCell(1).setCellValue(mov.getTipo());

                // Descripción
                row.createCell(2).setCellValue(mov.getDescripcion());

                // Monto
                row.createCell(3).setCellValue(mov.getMonto().doubleValue());

                // Fecha
                row.createCell(4).setCellValue(mov.getFecha().format(formatter));
            }

            // Ajustar ancho de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    /**
     * Exporta asistencias a Excel
     * Encabezados: Nombres, Apellido Paterno, Apellido Materno, Estado,
     * Puntualidad, Motivo, Fecha de asistencia
     */
    public byte[] exportAsistencias(List<SocioAsistenciaEntity> asistencias) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Asistencias");

            // Estilo para encabezados
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Crear fila de encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = { "Nombres", "Apellido Paterno", "Apellido Materno",
                    "Estado", "Puntualidad", "Motivo", "Fecha de asistencia" };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Formato de fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Llenar datos
            int rowNum = 1;
            for (SocioAsistenciaEntity asistencia : asistencias) {
                Row row = sheet.createRow(rowNum++);

                // Nombres
                row.createCell(0).setCellValue(asistencia.getSocio().getNombresocio());

                // Apellido Paterno
                row.createCell(1).setCellValue(asistencia.getSocio().getApellidopaternosocio());

                // Apellido Materno
                String apellidoMaterno = asistencia.getSocio().getApellidomaternosocio();
                row.createCell(2).setCellValue(apellidoMaterno != null ? apellidoMaterno : "");

                // Estado
                row.createCell(3).setCellValue(asistencia.getEstado());

                // Puntualidad
                String puntualidad = asistencia.getPuntualidad();
                row.createCell(4).setCellValue(puntualidad != null ? puntualidad : "");

                // Motivo
                String motivo = asistencia.getMotivo();
                row.createCell(5).setCellValue(motivo != null ? motivo : "");

                // Fecha de asistencia
                row.createCell(6).setCellValue(asistencia.getAsistencia().getFecha().format(formatter));
            }

            // Ajustar ancho de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
