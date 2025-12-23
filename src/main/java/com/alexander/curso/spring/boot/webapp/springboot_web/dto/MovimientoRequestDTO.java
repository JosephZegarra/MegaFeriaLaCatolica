package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoRequestDTO {
    private String numero_carnet;
    private String tipo;
    private BigDecimal monto;
    private String descripcion;

    //metodos getter and setter

    public String getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(String numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
