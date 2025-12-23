package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

//dto es un objeto simple que se utiliza para enviar un objetos por las diferentes
//capas

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
//@Getter
//@Setter

public class SocioDTO
{
    //private int idsocio; ORIGINAL

    private Integer idsocio;

    private String nombresocio;


    private String apellidopaternosocio;
    private String apellidomaternosocio;
    private String direccion;
    private String distrito;
    private String estadocivil;

    private Integer intentos_fallidos;
    private Boolean cuenta_bloqueada;
    private LocalDateTime fecha_bloqueo;


    private String dni;

    private String numero_carnet;

    private String telefono;


    private String contrasena;

    private String estado;
    private String tipo;
    private String correo;
    //nuevos metodos
    private String token;
    private Boolean correoVerificado;

//--------------Contructor
public SocioDTO(Integer idsocio, String nombresocio, String apellidopaternosocio, String direccion, String telefono) {
    this.idsocio = idsocio;
    this.nombresocio = nombresocio;
    this.apellidopaternosocio = apellidopaternosocio;
    this.telefono = telefono;
    this.direccion=direccion;
}

public SocioDTO()
{

}

//-----------------------------------------------------------

    public LocalDateTime getFecha_bloqueo() {
        return fecha_bloqueo;
    }

    public void setFecha_bloqueo(LocalDateTime fecha_bloqueo) {
        this.fecha_bloqueo = fecha_bloqueo;
    }

    public Integer getIntentos_fallidos() {
        return intentos_fallidos;
    }

    public void setIntentos_fallidos(Integer intentos_fallidos) {
        this.intentos_fallidos = intentos_fallidos;
    }

    public Boolean getCuenta_bloqueada() {
        return cuenta_bloqueada;
    }

    public void setCuenta_bloqueada(Boolean cuenta_bloqueada) {
        this.cuenta_bloqueada = cuenta_bloqueada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(String numero_carnet) {
        this.numero_carnet = numero_carnet;
    }
    /*
    public int getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(int idsocio) {
        this.idsocio = idsocio;
    }
*/

    public Integer getIdsocio() {
        return idsocio;
    }

    public void setIdsocio(Integer idsocio) {
        this.idsocio = idsocio;
    }

    public String getNombresocio() {
        return nombresocio;
    }

    public void setNombresocio(String nombresocio) {
        this.nombresocio = nombresocio;
    }

    public String getApellidopaternosocio() {
        return apellidopaternosocio;
    }

    public void setApellidopaternosocio(String apellidopaternosocio) {
        this.apellidopaternosocio = apellidopaternosocio;
    }

    public String getApellidomaternosocio() {
        return apellidomaternosocio;
    }

    public void setApellidomaternosocio(String apellidomaternosocio) {
        this.apellidomaternosocio = apellidomaternosocio;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getCorreoVerificado() {
        return correoVerificado;
    }

    public void setCorreoVerificado(Boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }
}
