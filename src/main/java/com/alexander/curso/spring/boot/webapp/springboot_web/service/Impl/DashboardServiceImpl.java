package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.AsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioAsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DashboardService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService
{
    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private SocioAsistenciaRepository socioAsistenciaRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private MovimientoService movimientoService;

    @Override
    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();

        // ==== 1. KPIs SOCIOS ====
        data.put("totalSocios", socioRepository.count());
        data.put("sociosActivos", socioRepository.countByEstado("Activo"));
        data.put("sociosInactivos", socioRepository.countByEstado("Inactivo"));
        data.put("sociosBloqueados", socioRepository.countByEstado("Bloqueado"));

        // ==== 2. Tendencia (últimos 7 días) ====

        LocalDate hoy = LocalDate.now();
        LocalDate hace7dias = hoy.minusDays(6);

        // Llamar al repositorio usando LocalDate (NO LocalDateTime)
        List<ResumenAsistenciaDTO> asistenciaDiaria =
                socioAsistenciaRepository.obtenerResumenPorFecha(hace7dias, hoy);

        data.put("asistenciaDiaria", asistenciaDiaria);
        //aqui
        //data.put("movimientos",movimientoService.getMovimientosUltimos7Dias());
        //data.put("movimientos", movimientoService.getMovimientosMesActual());
        // ==== 3. Conteo por estado de asistencia ====
        data.put("asistenciaEstado", socioAsistenciaRepository.contarPorEstado());

        // ==== 4. Top deudores ====
        data.put("topDeudores", socioAsistenciaRepository.topDeudores());

        return data;
    }
}
