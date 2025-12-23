package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class AsistenciaDTO
{
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idasistencia;
    private LocalDateTime fecha;
    private String tipoevento;
    private String descripcion;
    private List<Integer>idsocios;//Lista de socios que asistieron

    private boolean puedoTomarAsistencia;
    //Metodos Getter and Setter


    public boolean isPuedoTomarAsistencia() {
        return puedoTomarAsistencia;
    }

    public void setPuedoTomarAsistencia(boolean puedoTomarAsistencia) {
        this.puedoTomarAsistencia = puedoTomarAsistencia;
    }

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }


    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public List<Integer> getIdsocios() {
        return idsocios;
    }

    public void setIdsocios(List<Integer> idsocios) {
        this.idsocios = idsocios;
    }
}
