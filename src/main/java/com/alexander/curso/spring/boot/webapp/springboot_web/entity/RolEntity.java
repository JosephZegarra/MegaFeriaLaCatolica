package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="rol")
public class RolEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;

    @Column(nullable=false)
    private String nombrerol;

    //Metodos Getter and Setter

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
