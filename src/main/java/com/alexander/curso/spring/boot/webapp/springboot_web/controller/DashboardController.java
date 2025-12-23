package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DashboardService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Usar LocalDate directamente
        LocalDate from = LocalDate.now().minusDays(6); // hace 6 días
        LocalDate to = LocalDate.now();

        // hoy
        LocalDate fr=LocalDate.now().withDayOfMonth(1);
        LocalDate tos = LocalDate.now();
        //LocalDateTime from=fromDate.atStartOfDay();
        //LocalDateTime to=toDate.plusDays(1).atStartOfDay();
        model.addAttribute("dashboardData", dashboardService.getDashboardData());
        model.addAttribute("resumenAsistencia", asistenciaService.obtenerResumenPorFecha(from, to));
        List<MovimientoResumenDTO> movimientos = movimientoService.MovimientosUltimos7Dias();
        model.addAttribute("movimientos", movimientos);
        return "Dashboard";
    }

}
