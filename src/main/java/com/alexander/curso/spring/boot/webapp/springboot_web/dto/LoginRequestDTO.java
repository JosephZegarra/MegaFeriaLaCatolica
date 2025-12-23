package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

@Data
public class LoginRequestDTO
{
    private String numero_carnet;
    private String contrasena;
    private String rol;

    //metodos getter and setter

    public String getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(String numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
