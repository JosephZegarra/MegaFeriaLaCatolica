package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimientoDTO
{

    private Integer idmovimiento;

    private Integer idsocio;

    private String tipo;
    private String descripcion;
    private BigDecimal monto;
    private LocalDate fecha;

    private String nombreSocio;
    private String apellidoPaternoSocio;
    // Crear constrcutor

    public MovimientoDTO(Integer idsocio,String tipo, String descripcion, BigDecimal monto, LocalDate fecha) {
        this.idsocio=idsocio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public MovimientoDTO()
    {

    }


    //metodos getter ande setter


    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getApellidoPaternoSocio() {
        return apellidoPaternoSocio;
    }

    public void setApellidoPaternoSocio(String apellidoPaternoSocio) {
        this.apellidoPaternoSocio = apellidoPaternoSocio;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
