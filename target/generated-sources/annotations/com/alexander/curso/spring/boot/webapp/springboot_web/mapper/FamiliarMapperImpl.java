package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.FamiliaresEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T15:36:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class FamiliarMapperImpl implements FamiliarMapper {

    @Override
    public FamiliaresDTO FamiliaresEntityAFamiliaresDTO(FamiliaresEntity familiaresEntity) {
        if ( familiaresEntity == null ) {
            return null;
        }

        FamiliaresDTO familiaresDTO = new FamiliaresDTO();

        familiaresDTO.setIdsocio( familiaresEntitySocioIdsocio( familiaresEntity ) );
        familiaresDTO.setIdfamiliares( familiaresEntity.getIdfamiliares() );
        familiaresDTO.setNombrefamiliar( familiaresEntity.getNombrefamiliar() );
        familiaresDTO.setApellidopaternofamiliar( familiaresEntity.getApellidopaternofamiliar() );
        familiaresDTO.setApellidomaternofamiliar( familiaresEntity.getApellidomaternofamiliar() );
        familiaresDTO.setParentesco( familiaresEntity.getParentesco() );
        familiaresDTO.setDni( familiaresEntity.getDni() );
        familiaresDTO.setTelefono( familiaresEntity.getTelefono() );
        familiaresDTO.setEssocio( familiaresEntity.isEssocio() );

        return familiaresDTO;
    }

    @Override
    public FamiliaresEntity FamiliaresDTOAFamiliaresEntity(FamiliaresDTO familiaresDTO) {
        if ( familiaresDTO == null ) {
            return null;
        }

        FamiliaresEntity familiaresEntity = new FamiliaresEntity();

        familiaresEntity.setSocio( map( familiaresDTO.getIdsocio() ) );
        familiaresEntity.setIdfamiliares( familiaresDTO.getIdfamiliares() );
        familiaresEntity.setNombrefamiliar( familiaresDTO.getNombrefamiliar() );
        familiaresEntity.setApellidopaternofamiliar( familiaresDTO.getApellidopaternofamiliar() );
        familiaresEntity.setApellidomaternofamiliar( familiaresDTO.getApellidomaternofamiliar() );
        familiaresEntity.setParentesco( familiaresDTO.getParentesco() );
        familiaresEntity.setTelefono( familiaresDTO.getTelefono() );
        familiaresEntity.setDni( familiaresDTO.getDni() );
        familiaresEntity.setEssocio( familiaresDTO.isEssocio() );

        return familiaresEntity;
    }

    private Integer familiaresEntitySocioIdsocio(FamiliaresEntity familiaresEntity) {
        if ( familiaresEntity == null ) {
            return null;
        }
        SocioEntity socio = familiaresEntity.getSocio();
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
