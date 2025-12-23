package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="socioasistencia")
public class SocioAsistenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsocioasistencia")
    private Integer idSocioAsistencia;

    @ManyToOne
    @JoinColumn(name = "idasistencia", nullable = false)
    private AsistenciaEntity asistencia;

    @ManyToOne
    @JoinColumn(name = "idsocio", nullable = false)
    private SocioEntity socio;

    @Column(name = "estado")
    private String estado;

    @Column(name = "puntualidad")
    private String puntualidad;

    @Column(name = "motivo")
    private String motivo;

    @OneToMany(mappedBy = "socioAsistenciaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<DeudaEntity> deudas;

    //metodos getter and setter

    public Integer getIdSocioAsistencia() {
        return idSocioAsistencia;
    }

    public void setIdSocioAsistencia(Integer idSocioAsistencia) {
        this.idSocioAsistencia = idSocioAsistencia;
    }

    public AsistenciaEntity getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(AsistenciaEntity asistencia) {
        this.asistencia = asistencia;
    }

    public SocioEntity getSocio() {
        return socio;
    }

    public void setSocio(SocioEntity socio) {
        this.socio = socio;
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