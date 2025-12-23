package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="asistencia")
public class AsistenciaEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idasistencia;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String tipoevento;

    @Column(nullable = false)
    private String descripcion;

//    ACA
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "socioasistencia",
            joinColumns = @JoinColumn(name = "idasistencia"),
            inverseJoinColumns = @JoinColumn(name = "idsocio")
    )
    private List<SocioEntity>socios;

    public AsistenciaEntity() {
    }

    /*
    @PrePersist
    public void prePersist() {
        //this.fecha = LocalDateTime.now();
    }
    */


    //metodos getter and setter

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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


    public List<SocioEntity> getSocios() {
        return socios;
    }

    public void setSocios(List<SocioEntity> socios) {
        this.socios = socios;
    }
}
