package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper
{
    AsistenciaDTO AsistenciaEntityAAsistenciaDTO(AsistenciaEntity asistenciaEntity);
    AsistenciaEntity AsistenciaDTOAAsistenciaEntity(AsistenciaDTO asistenciaDTO);
    List<AsistenciaDTO> asistenciaEntityListAAsistenciaDTO(List<AsistenciaEntity>asistencia);
}
