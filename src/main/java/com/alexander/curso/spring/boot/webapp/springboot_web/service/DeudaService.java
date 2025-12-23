package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;

import java.math.BigDecimal;
import java.util.List;

public interface DeudaService
{
    public void generardeudaporasistencia(List<SocioAsistenciaEntity> listsocioasistencia);
    public BigDecimal obtenerdeudaporsocio(Integer idsocio);
    public List<DeudaEntity> obtenerDeudaPendientePorSocio(Integer idsocio);
    public DeudaEntity obtenerDeudaPorId(Integer iddeuda);
    public SocioDTO obtenerSocioPorIdDeuda(Integer iddeuda);

}
