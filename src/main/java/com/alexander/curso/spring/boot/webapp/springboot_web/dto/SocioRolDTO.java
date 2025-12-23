package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SocioRolDTO
{
    private Integer idrolsocio;
    private Integer idrol;
    private Integer idsocio;

    private LocalDate fechainicio;

    private LocalDate fechafin;

    //Metodos Getter and Setter

    public Integer getIdrolsocio() {
        return idrolsocio;
    }

    public void setIdrolsocio(Integer idrolsocio) {
        this.idrolsocio = idrolsocio;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    public LocalDate getFechafin() {
        return fechafin;
    }

    public void setFechafin(LocalDate fechafin) {
        this.fechafin = fechafin;
    }
}
