package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.MovimientoRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioAsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DashboardService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.ExcelExportService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private SocioAsistenciaRepository socioAsistenciaRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Usar LocalDate directamente
        LocalDate from = LocalDate.now().minusDays(6); // hace 6 días
        LocalDate to = LocalDate.now();

        // hoy
        LocalDate fr = LocalDate.now().withDayOfMonth(1);
        LocalDate tos = LocalDate.now();
        // LocalDateTime from=fromDate.atStartOfDay();
        // LocalDateTime to=toDate.plusDays(1).atStartOfDay();
        model.addAttribute("dashboardData", dashboardService.getDashboardData());
        model.addAttribute("resumenAsistencia", asistenciaService.obtenerResumenPorFecha(from, to));
        List<MovimientoResumenDTO> movimientos = movimientoService.MovimientosUltimos7Dias();
        model.addAttribute("movimientos", movimientos);
        return "Dashboard";
    }

    /**
     * Endpoint para exportar Ingresos/Egresos a Excel
     */
    @GetMapping("/exportar-movimientos")
    public ResponseEntity<byte[]> exportarMovimientos(
            @RequestParam("mes") int mes,
            @RequestParam("anio") int anio) {

        try {
            System.out.println("=== EXPORTAR MOVIMIENTOS ===");
            System.out.println("Mes: " + mes + ", Año: " + anio);

            // Obtener movimientos del mes y año seleccionados
            List<MovimientoEntity> movimientos = movimientoRepository.findByMesYAnio(mes, anio);
            System.out.println("Movimientos encontrados: " + movimientos.size());

            // Generar Excel
            byte[] excelBytes = excelExportService.exportMovimientos(movimientos);
            System.out.println("Excel generado: " + excelBytes.length + " bytes");

            // Preparar respuesta
            String fileName = String.format("Movimientos_%02d_%d.xlsx", mes, anio);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(
                    MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            headers.setContentLength(excelBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);

        } catch (Exception e) {
            System.err.println("ERROR exportando movimientos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint para exportar Asistencias a Excel
     */
    @GetMapping("/exportar-asistencias")
    public ResponseEntity<byte[]> exportarAsistencias(
            @RequestParam("mes") int mes,
            @RequestParam("anio") int anio) {

        try {
            System.out.println("=== EXPORTAR ASISTENCIAS ===");
            System.out.println("Mes: " + mes + ", Año: " + anio);

            // Obtener asistencias del mes y año seleccionados
            List<SocioAsistenciaEntity> asistencias = socioAsistenciaRepository.findByMesYAnio(mes, anio);
            System.out.println("Asistencias encontradas: " + asistencias.size());

            // Generar Excel
            byte[] excelBytes = excelExportService.exportAsistencias(asistencias);
            System.out.println("Excel generado: " + excelBytes.length + " bytes");

            // Preparar respuesta
            String fileName = String.format("Asistencias_%02d_%d.xlsx", mes, anio);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(
                    MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            headers.setContentLength(excelBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);

        } catch (Exception e) {
            System.err.println("ERROR exportando asistencias: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
