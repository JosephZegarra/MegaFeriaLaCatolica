package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioRolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioRolMapper
{
    SocioRolDTO SocioRolEntityASocioRolDTO(SocioRolEntity socioRolEntity);
    SocioRolEntity SocioRolDTOASocioRolEntity(SocioRolDTO socioRolDTO);
}
