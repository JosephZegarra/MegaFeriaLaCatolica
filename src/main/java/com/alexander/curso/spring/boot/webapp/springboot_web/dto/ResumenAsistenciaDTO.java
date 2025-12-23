package com.alexander.curso.spring.boot.webapp.springboot_web.dto;
import java.time.LocalDate;

public class ResumenAsistenciaDTO {

    private LocalDate fecha;
    private Long totalPresente;
    private Long totalAusente;
    private Long totalJustificado;

    public ResumenAsistenciaDTO(java.sql.Date  fecha, Long totalPresente, Long totalAusente, Long totalJustificado) {
        this.fecha = fecha.toLocalDate();
        this.totalPresente = totalPresente;
        this.totalAusente = totalAusente;
        this.totalJustificado = totalJustificado;
    }

    // getters y setters


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getTotalPresente() {
        return totalPresente;
    }

    public void setTotalPresente(Long totalPresente) {
        this.totalPresente = totalPresente;
    }

    public Long getTotalAusente() {
        return totalAusente;
    }

    public void setTotalAusente(Long totalAusente) {
        this.totalAusente = totalAusente;
    }

    public Long getTotalJustificado() {
        return totalJustificado;
    }

    public void setTotalJustificado(Long totalJustificado) {
        this.totalJustificado = totalJustificado;
    }
}
