package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioRolEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T15:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class SocioRolMapperImpl implements SocioRolMapper {

    @Override
    public SocioRolDTO SocioRolEntityASocioRolDTO(SocioRolEntity socioRolEntity) {
        if ( socioRolEntity == null ) {
            return null;
        }

        SocioRolDTO socioRolDTO = new SocioRolDTO();

        socioRolDTO.setIdrolsocio( socioRolEntity.getIdrolsocio() );
        socioRolDTO.setFechainicio( socioRolEntity.getFechainicio() );
        socioRolDTO.setFechafin( socioRolEntity.getFechafin() );

        return socioRolDTO;
    }

    @Override
    public SocioRolEntity SocioRolDTOASocioRolEntity(SocioRolDTO socioRolDTO) {
        if ( socioRolDTO == null ) {
            return null;
        }

        SocioRolEntity socioRolEntity = new SocioRolEntity();

        socioRolEntity.setIdrolsocio( socioRolDTO.getIdrolsocio() );
        socioRolEntity.setFechafin( socioRolDTO.getFechafin() );
        socioRolEntity.setFechainicio( socioRolDTO.getFechainicio() );

        return socioRolEntity;
    }
}
