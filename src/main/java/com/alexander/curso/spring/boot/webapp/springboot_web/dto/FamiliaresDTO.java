package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FamiliaresDTO
{
    //@Id//AGREGADO no original
    //@GeneratedValue(strategy = GenerationType.IDENTITY)//agregado o original
    private Integer idfamiliares;

    private Integer idsocio;

    @NotNull(message="El nombre no debe ser nulo")
    @Size(min=3, max=100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombrefamiliar;
    private String apellidopaternofamiliar;
    private String apellidomaternofamiliar;

    @NotNull(message = "El parentesco no debe ser nulo ")//-
    private String parentesco;
    @NotNull(message = "El telefono no debe ser nulo ")//-
    private String telefono;

    @NotNull(message = "El DNI no debe ser nulo ")
    @Size(max=8, message = "El DNI debe tener máximo 8 caracteres")
    private String dni;

    @NotNull(message = "El essocio no debe ser nulo ")
    private boolean essocio;

    //Metodos Getter and Setter


    public Integer getIdfamiliares() {
        return idfamiliares;
    }

    public void setIdfamiliares(Integer idfamiliares) {
        this.idfamiliares = idfamiliares;
    }

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEssocio() {
        return essocio;
    }

    public void setEssocio(boolean essocio) {
        this.essocio = essocio;
    }
}
