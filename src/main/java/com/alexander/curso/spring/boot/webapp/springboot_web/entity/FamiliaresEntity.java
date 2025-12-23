package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="familiares")
public class FamiliaresEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idfamiliares;

    @ManyToOne
    @JoinColumn(name="idsocio",nullable = false)
    private SocioEntity socio;

    @NotNull(message = "El nombre de la categoría no puede ser nulo")
    @Size(min=3, max=100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, unique=true, name="nombrefamiliar")
    private String nombrefamiliar;

    @Column(nullable=true)
    private String apellidopaternofamiliar;
    @Column(nullable=true)
    private String apellidomaternofamiliar;
    @Column(nullable=false)
    private String parentesco;
    @Column(nullable=false)
    private String telefono;
    @Column(nullable=false)
    private String dni;
    @Column(nullable=false)
    private boolean essocio;
// METODOS GETTER AND SETTER

    public Integer getIdfamiliares() {
        return idfamiliares;
    }

    public void setIdfamiliares(Integer idfamiliares) {
        this.idfamiliares = idfamiliares;
    }

    public SocioEntity getSocio() {
        return socio;
    }

    public void setSocio(SocioEntity socio) {
        this.socio = socio;
    }

    public String getNombrefamiliar() {
        return nombrefamiliar;
    }

    public void setNombrefamiliar(String nombrefamiliar) {
        this.nombrefamiliar = nombrefamiliar;
    }

    public String getApellidopaternofamiliar() {
        return apellidopaternofamiliar;
    }

    public void setApellidopaternofamiliar(String apellidopaternofamiliar) {
        this.apellidopaternofamiliar = apellidopaternofamiliar;
    }

    public String getApellidomaternofamiliar() {
        return apellidomaternofamiliar;
    }

    public void setApellidomaternofamiliar(String apellidomaternofamiliar) {
        this.apellidomaternofamiliar = apellidomaternofamiliar;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isEssocio() {
        return essocio;
    }

    public void setEssocio(boolean essocio) {
        this.essocio = essocio;
    }
}
