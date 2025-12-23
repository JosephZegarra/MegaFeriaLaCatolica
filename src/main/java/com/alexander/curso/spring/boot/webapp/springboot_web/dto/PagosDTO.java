package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagosDTO
{
    private Integer idpago;
    private Integer iddeuda;
    private LocalDate fecha;
    private BigDecimal monto;
    private String carnetRegistrador;
    private String nombreRegistrador;


    //meotodos getter and setter


    public String getNombreRegistrador() {
        return nombreRegistrador;
    }

    public void setNombreRegistrador(String nombreRegistrador) {
        this.nombreRegistrador = nombreRegistrador;
    }

    public String getCarnetRegistrador() {
        return carnetRegistrador;
    }

    public void setCarnetRegistrador(String carnetRegistrador) {
        this.carnetRegistrador = carnetRegistrador;
    }

    public Integer getIdpago() {
        return idpago;
    }

    public void setIdpago(Integer idpago) {
        this.idpago = idpago;
    }

    public Integer getIddeuda() {
        return iddeuda;
    }

    public void setIddeuda(Integer iddeuda) {
        this.iddeuda = iddeuda;
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
}
