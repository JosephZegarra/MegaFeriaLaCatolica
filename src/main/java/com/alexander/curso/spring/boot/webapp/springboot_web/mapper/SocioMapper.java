package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

//El codigo es una interfax


import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocioMapper
{
    //Acuerdate entity es base de datos y DTO por segurdad cada vez que el cliente solicita la lista de socios la lista que
    //recibimos la petición de la base de datos con entity y lo convertimos en DTO y eso le mostramos al usuario

    //LO QUE ESTA ENTRE PARENTESIS ES EL PARAMETRO QUE SERA CONVERTIDO

    //SocioMapper INSTANCE = Mappers.getMapper(SocioMapper.class);(Impo)
    SocioDTO SocioEntityASocioDTO(SocioEntity socioEntity);
    SocioEntity SocioDTOASocioEntity(SocioDTO socioDTO);
    List<SocioDTO> socioDTOASocioDTO(List<SocioEntity>socios); //LA LISTA DE SOCIOS QUE ES RECOJIDO DE LA BASE DE DATOS
                                                                //SE CONVERTIRA  EN UNA LISTA DE SOCIOS DTO
}
