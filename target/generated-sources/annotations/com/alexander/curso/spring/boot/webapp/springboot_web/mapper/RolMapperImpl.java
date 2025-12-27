package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.RolEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-26T21:50:19-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class RolMapperImpl implements RolMapper {

    @Override
    public RolDTO RolEntityARolDTO(RolEntity rolEntity) {
        if ( rolEntity == null ) {
            return null;
        }

        RolDTO rolDTO = new RolDTO();

        rolDTO.setIdrol( rolEntity.getIdrol() );
        rolDTO.setNombrerol( rolEntity.getNombrerol() );

        return rolDTO;
    }

    @Override
    public RolEntity RolDTOARolEntity(RolDTO rolDTO) {
        if ( rolDTO == null ) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();

        rolEntity.setIdrol( rolDTO.getIdrol() );
        rolEntity.setNombrerol( rolDTO.getNombrerol() );

        return rolEntity;
    }
}
