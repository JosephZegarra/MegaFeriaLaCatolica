package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;

import java.util.List;

public interface SocioService
{
    public List<SocioDTO> listar();

    public SocioDTO BuscarPorId(Integer idsocio);

    public SocioDTO guardar(SocioDTO socioDTO);

    public SocioDTO editar(SocioDTO socioDTO);

    public void borrar(Integer idsocio);

    //public SocioDTO BuscarPorNumero_CarnetYContrasena(String numero_carnet,String contrasena);

    public Integer obtenerIdSocioPorCarnet(String numero_carnet);

    public boolean confirmarCorreo(String token);

    public SocioDTO BuscarPorNumero_Carnet(String numero_carnet);

    public SocioDTO validarSocio(String numero_carnet,String contrasena);
    //public String obtenerrolporsocio(String numero_carnet);//Verficiar despues

    public SocioEntity buscarEntidadPorNumeroCarnet(String numeroCarnet);
}
