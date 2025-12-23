package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.MovimientoMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.MovimientoRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoImpl implements MovimientoService
{
    @Autowired
    MovimientoMapper movimientoMapper;

    @Autowired
    MovimientoRepository movimientoRepository;


    @Override
    public MovimientoDTO guardar(MovimientoDTO movimientoDTO)
    {
        MovimientoEntity movimientoEntity=movimientoMapper.MovimientoDTOAMovimientoEntity(movimientoDTO);
        MovimientoEntity savedEntity=movimientoRepository.save(movimientoEntity);

        return movimientoMapper.MovimientoEntityAMovimientoDTO(savedEntity);
    }

    public List<MovimientoDTO> listarIngresos()
    {
        //creo la lista le agregos dos campos para que me muestre visualmente el nombre y apellido sinn tener que ir a la base dew datos
        return movimientoRepository.findByTipo("Ingreso").stream().map(s->{
            MovimientoDTO movimiento=new MovimientoDTO();
            movimiento.setIdsocio(s.getSocio().getIdsocio());
            movimiento.setNombreSocio(s.getSocio().getNombresocio());
            movimiento.setApellidoPaternoSocio(s.getSocio().getApellidopaternosocio());
            movimiento.setTipo(s.getTipo());
            movimiento.setDescripcion(s.getDescripcion());
            movimiento.setMonto(s.getMonto());
            movimiento.setFecha(s.getFecha());
            return movimiento;
        }).toList();
    }

    public List<MovimientoDTO> listarEgresos()
    {
        return movimientoRepository.findByTipo("Egreso").stream().map(s->{
            MovimientoDTO movimiento=new MovimientoDTO();
            movimiento.setIdsocio(s.getSocio().getIdsocio());
            movimiento.setNombreSocio(s.getSocio().getNombresocio());
            movimiento.setApellidoPaternoSocio(s.getSocio().getApellidopaternosocio());
            movimiento.setTipo(s.getTipo());
            movimiento.setDescripcion(s.getDescripcion());
            movimiento.setMonto(s.getMonto());
            movimiento.setFecha(s.getFecha());
            return movimiento;
        }).toList();
    }

    @Override
    public List<MovimientoResumenDTO> MovimientosUltimos7Dias() {
        //LocalDate hoy = LocalDate.now();
        //LocalDate hace7Dias = hoy.minusDays(6);
        //return movimientoRepository.obtenerMovimientosPorFecha(hace7Dias, hoy);
        LocalDate fechaMax = movimientoRepository.obtenerFechaMaxima(); // método que devuelve MAX(fecha)
        LocalDate desde = fechaMax.minusDays(6); // últimos 7 días desde la fecha más reciente
        System.out.println("RANGO => " + desde + " HASTA " + fechaMax);
        return movimientoRepository.obtenerMovimientosPorFecha(desde, fechaMax);
    }


/*
    @Override
    public List<MovimientoResumenDTO> obtenerMovimientosPorMes(LocalDate from, LocalDate to) {
        return movimientoRepository.obtenerMovimientosPorMes(from, to);
    }
    /*
*/
    @Override
    public List<MovimientoResumenDTO> getMovimientosMesActual()
    {
        LocalDate now = LocalDate.now();
        LocalDate from = now.withDayOfMonth(1); // 1 de diciembre
        return movimientoRepository.obtenerMovimientosPorFecha(from, now);
    }

}
