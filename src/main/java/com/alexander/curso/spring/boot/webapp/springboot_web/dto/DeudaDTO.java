package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DeudaDTO
{
    private Integer iddeuda;
    private Integer idsocioasistencia;
    private Integer idtipodeuda;
    private LocalDate fecha;
    private BigDecimal monto;
    private String estado;

    //Metodos getter and setter

    public Integer getIddeuda() {
        return iddeuda;
    }

    public void setIddeuda(Integer iddeuda) {
        this.iddeuda = iddeuda;
    }

    public Integer getIdsocioasistencia() {
        return idsocioasistencia;
    }

    public void setIdsocioasistencia(Integer idsocioasistencia) {
        this.idsocioasistencia = idsocioasistencia;
    }

    public Integer getIdtipodeuda() {
        return idtipodeuda;
    }

    public void setIdtipodeuda(Integer idtipodeuda) {
        this.idtipodeuda = idtipodeuda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
