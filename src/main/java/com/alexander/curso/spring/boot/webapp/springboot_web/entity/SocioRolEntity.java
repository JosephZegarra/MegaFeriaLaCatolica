package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="rolsocio")
public class SocioRolEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrolsocio")
    private Integer idrolsocio;

    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "idsocio", nullable = false)
    private SocioEntity socio;

    @Column(name="fechainicio")
    private LocalDate fechainicio;

    @Column(name="fechafin")
    private LocalDate fechafin;

    public Integer getIdrolsocio() {
        return idrolsocio;
    }

    public void setIdrolsocio(Integer idrolsocio) {
        this.idrolsocio = idrolsocio;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public SocioEntity getSocio() {
        return socio;
    }

    public void setSocio(SocioEntity socio) {
        this.socio = socio;
    }

    public LocalDate getFechafin() {
        return fechafin;
    }

    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }

    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }
}
