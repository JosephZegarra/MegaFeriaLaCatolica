package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.PagosDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PagosService
{
    public PagosDTO registrarpago(Integer iddeuda, BigDecimal monto,String numero_carnet);
    public List<PagosDTO> obtenerPagosPorSocio(Integer idsocio);
}
