package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="pagos")
public class PagosEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;

    @ManyToOne
    @JoinColumn(name="iddeuda",nullable = false)
    private DeudaEntity deuda;

    @Column(nullable=false)
    private LocalDate fecha;

    @Column(nullable = false)
    private BigDecimal monto;

    private String carnetRegistrador;
    //Metodos gette and setter


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

    public DeudaEntity getDeuda() {
        return deuda;
    }

    public void setDeuda(DeudaEntity deuda) {
        this.deuda = deuda;
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
