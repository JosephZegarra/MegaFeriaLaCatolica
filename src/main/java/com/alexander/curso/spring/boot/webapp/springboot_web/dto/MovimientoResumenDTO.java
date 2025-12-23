package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimientoResumenDTO
{
    private LocalDate fecha;

    private BigDecimal ingresos;
    private BigDecimal egresos;
    private BigDecimal saldo;

    public MovimientoResumenDTO(LocalDate fecha, BigDecimal ingresos, BigDecimal egresos, BigDecimal saldo) {
        this.fecha = fecha;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.saldo = saldo;
    }

    /*
    public MovimientoResumenDTO(int año, int mes, BigDecimal ingresos, BigDecimal egresos, BigDecimal saldo) {
        this.año = año;
        this.mes = mes;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.saldo = saldo;
    }
    */

    //getter and setter

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void setIngresos(BigDecimal ingresos) {
        this.ingresos = ingresos;
    }

    public BigDecimal getEgresos() {
        return egresos;
    }

    public void setEgresos(BigDecimal egresos) {
        this.egresos = egresos;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


    //Metodos gettwer and Setter
/*
    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void setIngresos(BigDecimal ingresos) {
        this.ingresos = ingresos;
    }

    public BigDecimal getEgresos() {
        return egresos;
    }

    public void setEgresos(BigDecimal egresos) {
        this.egresos = egresos;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    */

}
