package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

@Data
public class TipoDeudaDTO
{
    private Integer idtipodeuda;
    private String nombrerol;

    //metodos getter and setter


    public Integer getIdtipodeuda() {
        return idtipodeuda;
    }

    public void setIdtipodeuda(Integer idtipodeuda) {
        this.idtipodeuda = idtipodeuda;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }
}
