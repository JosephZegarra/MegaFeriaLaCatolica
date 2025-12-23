package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService
{
    public MovimientoDTO guardar(MovimientoDTO movimientoDTO);
    public List<MovimientoDTO> listarIngresos();
    public List<MovimientoDTO> listarEgresos();
    public List<MovimientoResumenDTO> MovimientosUltimos7Dias();
   //public List<MovimientoResumenDTO> obtenerMovimientosPorMes(LocalDate from, LocalDate to);
    public List<MovimientoResumenDTO> getMovimientosMesActual();
}
