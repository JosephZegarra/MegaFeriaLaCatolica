package com.alexander.curso.spring.boot.webapp.springboot_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



@Entity
@Table(name="socios")

//RELACIONADO CON LA BASE DE DATOS
public class SocioEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsocio;

    @NotNull(message = "El nombre de la categoría no puede ser nulo")
    @Size(min=3, max=100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, unique=true, name="nombresocio")
    private String nombresocio;

    @Column(nullable=false)
    private String apellidopaternosocio;

    @Column(nullable=true)
    private String apellidomaternosocio;

    @Column(nullable=false)
    private String direccion;

    @Column(nullable=false)
    private String distrito;

    @Column(nullable=true)
    private String estadocivil;




    @NotNull(message = "El DNI no debe ser nulo ")
    @Size(max=8, message = "El DNI debe tener máximo 8 caracteres")
    private String dni;

    @Column(nullable=false)
    private String numero_carnet;

    @Column(nullable=false)
    private String telefono;

    @Column(nullable=false)
    private String contrasena;

    @Column(nullable=false)
    private String estado;

    @Column(nullable=false)
    private String tipo;

    @Column(nullable=true)
    private String correo;

    private String token;
    private Boolean correoVerificado;

    private Integer intentos_fallidos;
    private Boolean cuenta_bloqueada;

    private LocalDateTime fecha_bloqueo;
   //Cambie esto
    //@ManyToMany(mappedBy = "socios")
    //@JsonIgnore
    @ManyToMany(mappedBy = "socios")
    @JsonIgnore
    private List<AsistenciaEntity> asistencias;

    //----------METODOS GETTER AN SETTER


    public SocioEntity() {
    }

    public LocalDateTime getFecha_bloqueo() {
        return fecha_bloqueo;
    }

    public void setFecha_bloqueo(LocalDateTime fecha_bloqueo) {
        this.fecha_bloqueo = fecha_bloqueo;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

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

    //Metodos nuevos


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
}
