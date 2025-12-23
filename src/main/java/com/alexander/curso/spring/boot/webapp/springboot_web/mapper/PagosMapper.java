package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.PagosDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagosMapper {
    PagosDTO PagosEntityAPagosDTO(PagosEntity pagosEntity);
    PagosEntity PagosDTOAPagosEntity(PagosDTO pagosDTO);
    List<PagosDTO> pagosEntityListToDTO(List<PagosEntity> lista);
}
