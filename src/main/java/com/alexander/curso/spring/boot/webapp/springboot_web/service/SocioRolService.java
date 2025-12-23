package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;

import java.time.LocalDate;

public interface SocioRolService
{
    public void guardar(SocioDTO socioDTO,RolDTO rolDTO,LocalDate fechainicio,LocalDate fechafin);
}
