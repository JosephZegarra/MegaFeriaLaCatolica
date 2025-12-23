package com.alexander.curso.spring.boot.webapp.springboot_web.dto;


import lombok.Data;

@Data
public class RolDTO
{
    private Integer idrol;
    private String nombrerol;

    //Metodos Getter AND Setter

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }
}
