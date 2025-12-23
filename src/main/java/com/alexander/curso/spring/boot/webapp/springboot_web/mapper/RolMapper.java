package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.RolEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper
{
    RolDTO RolEntityARolDTO(RolEntity rolEntity);
    RolEntity RolDTOARolEntity(RolDTO rolDTO);

}
