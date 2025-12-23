package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T15:36:06-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AsistenciaMapperImpl implements AsistenciaMapper {

    @Override
    public AsistenciaDTO AsistenciaEntityAAsistenciaDTO(AsistenciaEntity asistenciaEntity) {
        if ( asistenciaEntity == null ) {
            return null;
        }

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO();

        asistenciaDTO.setIdasistencia( asistenciaEntity.getIdasistencia() );
        asistenciaDTO.setTipoevento( asistenciaEntity.getTipoevento() );
        asistenciaDTO.setDescripcion( asistenciaEntity.getDescripcion() );
        asistenciaDTO.setFecha( asistenciaEntity.getFecha() );

        return asistenciaDTO;
    }

    @Override
    public AsistenciaEntity AsistenciaDTOAAsistenciaEntity(AsistenciaDTO asistenciaDTO) {
        if ( asistenciaDTO == null ) {
            return null;
        }

        AsistenciaEntity asistenciaEntity = new AsistenciaEntity();

        asistenciaEntity.setIdasistencia( asistenciaDTO.getIdasistencia() );
        asistenciaEntity.setFecha( asistenciaDTO.getFecha() );
        asistenciaEntity.setTipoevento( asistenciaDTO.getTipoevento() );
        asistenciaEntity.setDescripcion( asistenciaDTO.getDescripcion() );

        return asistenciaEntity;
    }

    @Override
    public List<AsistenciaDTO> asistenciaEntityListAAsistenciaDTO(List<AsistenciaEntity> asistencia) {
        if ( asistencia == null ) {
            return null;
        }

        List<AsistenciaDTO> list = new ArrayList<AsistenciaDTO>( asistencia.size() );
        for ( AsistenciaEntity asistenciaEntity : asistencia ) {
            list.add( AsistenciaEntityAAsistenciaDTO( asistenciaEntity ) );
        }

        return list;
    }
}
