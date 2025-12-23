package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="deuda")
public class DeudaEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddeuda;
    //Metodos de las demás tablas
    @ManyToOne
    @JoinColumn(name="idsocioasistencia", nullable = false)
    private SocioAsistenciaEntity socioAsistenciaEntity;
    @ManyToOne
    @JoinColumn(name = "idtipodeuda", nullable = false)
    private TipoDeudaEntity tipoDeuda;
    //Metodos
    @Column(name="fecha")
    private LocalDate fecha=LocalDate.now();

    @Column(name="monto")
    private BigDecimal monto;

    @Column(name="estado")
    private String estado;

    @OneToMany(mappedBy = "deuda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagosEntity> pagos = new ArrayList<>();
    //Metodos Getter and Setter


    public Integer getIddeuda() {
        return iddeuda;
    }

    public void setIddeuda(Integer iddeuda) {
        this.iddeuda = iddeuda;
    }

    public SocioAsistenciaEntity getSocioAsistenciaEntity() {
        return socioAsistenciaEntity;
    }

    public void setSocioAsistenciaEntity(SocioAsistenciaEntity socioAsistenciaEntity) {
        this.socioAsistenciaEntity = socioAsistenciaEntity;
    }

    public TipoDeudaEntity getTipoDeuda() {
        return tipoDeuda;
    }

    public void setTipoDeuda(TipoDeudaEntity tipoDeuda) {
        this.tipoDeuda = tipoDeuda;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<PagosEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagosEntity> pagos) {
        this.pagos = pagos;
    }
}
