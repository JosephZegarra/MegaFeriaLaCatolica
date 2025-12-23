package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.PagosDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T15:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class PagosMapperImpl implements PagosMapper {

    @Override
    public PagosDTO PagosEntityAPagosDTO(PagosEntity pagosEntity) {
        if ( pagosEntity == null ) {
            return null;
        }

        PagosDTO pagosDTO = new PagosDTO();

        pagosDTO.setCarnetRegistrador( pagosEntity.getCarnetRegistrador() );
        pagosDTO.setIdpago( pagosEntity.getIdpago() );
        pagosDTO.setFecha( pagosEntity.getFecha() );
        pagosDTO.setMonto( pagosEntity.getMonto() );

        return pagosDTO;
    }

    @Override
    public PagosEntity PagosDTOAPagosEntity(PagosDTO pagosDTO) {
        if ( pagosDTO == null ) {
            return null;
        }

        PagosEntity pagosEntity = new PagosEntity();

        pagosEntity.setCarnetRegistrador( pagosDTO.getCarnetRegistrador() );
        pagosEntity.setIdpago( pagosDTO.getIdpago() );
        pagosEntity.setFecha( pagosDTO.getFecha() );
        pagosEntity.setMonto( pagosDTO.getMonto() );

        return pagosEntity;
    }

    @Override
    public List<PagosDTO> pagosEntityListToDTO(List<PagosEntity> lista) {
        if ( lista == null ) {
            return null;
        }

        List<PagosDTO> list = new ArrayList<PagosDTO>( lista.size() );
        for ( PagosEntity pagosEntity : lista ) {
            list.add( PagosEntityAPagosDTO( pagosEntity ) );
        }

        return list;
    }
}
