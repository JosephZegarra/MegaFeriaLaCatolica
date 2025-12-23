package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T15:36:07-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class MovimientoMapperImpl implements MovimientoMapper {

    @Override
    public MovimientoDTO MovimientoEntityAMovimientoDTO(MovimientoEntity movimientoEntity) {
        if ( movimientoEntity == null ) {
            return null;
        }

        MovimientoDTO movimientoDTO = new MovimientoDTO();

        movimientoDTO.setIdsocio( movimientoEntitySocioIdsocio( movimientoEntity ) );
        movimientoDTO.setIdmovimiento( movimientoEntity.getIdmovimiento() );
        movimientoDTO.setTipo( movimientoEntity.getTipo() );
        movimientoDTO.setDescripcion( movimientoEntity.getDescripcion() );
        movimientoDTO.setMonto( movimientoEntity.getMonto() );
        movimientoDTO.setFecha( movimientoEntity.getFecha() );

        return movimientoDTO;
    }

    @Override
    public MovimientoEntity MovimientoDTOAMovimientoEntity(MovimientoDTO movimientoDTO) {
        if ( movimientoDTO == null ) {
            return null;
        }

        MovimientoEntity movimientoEntity = new MovimientoEntity();

        movimientoEntity.setSocio( map( movimientoDTO.getIdsocio() ) );
        movimientoEntity.setIdmovimiento( movimientoDTO.getIdmovimiento() );
        movimientoEntity.setTipo( movimientoDTO.getTipo() );
        movimientoEntity.setDescripcion( movimientoDTO.getDescripcion() );
        movimientoEntity.setMonto( movimientoDTO.getMonto() );
        movimientoEntity.setFecha( movimientoDTO.getFecha() );

        return movimientoEntity;
    }

    private Integer movimientoEntitySocioIdsocio(MovimientoEntity movimientoEntity) {
        if ( movimientoEntity == null ) {
            return null;
        }
        SocioEntity socio = movimientoEntity.getSocio();
        if ( socio == null ) {
            return null;
        }
        Integer idsocio = socio.getIdsocio();
        if ( idsocio == null ) {
            return null;
        }
        return idsocio;
    }
}
