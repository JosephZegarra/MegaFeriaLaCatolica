package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="tipodeuda")
public class TipoDeudaEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtipodeuda;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal monto;

    //metodo getter and setter


    public Integer getIdtipodeuda() {
        return idtipodeuda;
    }

    public void setIdtipodeuda(Integer idtipodeuda) {
        this.idtipodeuda = idtipodeuda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
