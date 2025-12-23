package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

@Data
public class SocioAsistenciaDTO
{
    private Integer idsocioasistencia;
    private Integer idsocio;
    private Integer idasistencia;
    private String estado;
    private String puntualidad;
    private String motivo;

    //metodos getter and setter


    public Integer getIdsocioasistencia() {
        return idsocioasistencia;
    }

    public void setIdsocioasistencia(Integer idsocioasistencia) {
        this.idsocioasistencia = idsocioasistencia;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(String puntualidad) {
        this.puntualidad = puntualidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
