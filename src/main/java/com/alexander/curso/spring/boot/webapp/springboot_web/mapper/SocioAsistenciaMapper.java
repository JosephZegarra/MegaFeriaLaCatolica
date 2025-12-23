package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioAsistenciaMapper
{
    SocioAsistenciaDTO SocioAsistenciaEntityASocioAsistenciaDTO(SocioAsistenciaEntity socioAsistenciaEntity);
    SocioAsistenciaEntity SocioAsistenciaDTOASocioAsistenciaEntity(SocioAsistenciaDTO socioAsistenciaDTO);
}
