package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioRolEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-26T16:49:05-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
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
