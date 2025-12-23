package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
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
public class SocioMapperImpl implements SocioMapper {

    @Override
    public SocioDTO SocioEntityASocioDTO(SocioEntity socioEntity) {
        if ( socioEntity == null ) {
            return null;
        }

        SocioDTO socioDTO = new SocioDTO();

        socioDTO.setFecha_bloqueo( socioEntity.getFecha_bloqueo() );
        socioDTO.setIntentos_fallidos( socioEntity.getIntentos_fallidos() );
        socioDTO.setCuenta_bloqueada( socioEntity.getCuenta_bloqueada() );
        socioDTO.setDireccion( socioEntity.getDireccion() );
        socioDTO.setDistrito( socioEntity.getDistrito() );
        socioDTO.setEstadocivil( socioEntity.getEstadocivil() );
        socioDTO.setNumero_carnet( socioEntity.getNumero_carnet() );
        socioDTO.setIdsocio( socioEntity.getIdsocio() );
        socioDTO.setNombresocio( socioEntity.getNombresocio() );
        socioDTO.setApellidopaternosocio( socioEntity.getApellidopaternosocio() );
        socioDTO.setApellidomaternosocio( socioEntity.getApellidomaternosocio() );
        socioDTO.setTelefono( socioEntity.getTelefono() );
        socioDTO.setDni( socioEntity.getDni() );
        socioDTO.setContrasena( socioEntity.getContrasena() );
        socioDTO.setEstado( socioEntity.getEstado() );
        socioDTO.setTipo( socioEntity.getTipo() );
        socioDTO.setCorreo( socioEntity.getCorreo() );
        socioDTO.setToken( socioEntity.getToken() );
        socioDTO.setCorreoVerificado( socioEntity.getCorreoVerificado() );

        return socioDTO;
    }

    @Override
    public SocioEntity SocioDTOASocioEntity(SocioDTO socioDTO) {
        if ( socioDTO == null ) {
            return null;
        }

        SocioEntity socioEntity = new SocioEntity();

        socioEntity.setFecha_bloqueo( socioDTO.getFecha_bloqueo() );
        socioEntity.setDireccion( socioDTO.getDireccion() );
        socioEntity.setDistrito( socioDTO.getDistrito() );
        socioEntity.setEstadocivil( socioDTO.getEstadocivil() );
        socioEntity.setNumero_carnet( socioDTO.getNumero_carnet() );
        socioEntity.setContrasena( socioDTO.getContrasena() );
        socioEntity.setIdsocio( socioDTO.getIdsocio() );
        socioEntity.setNombresocio( socioDTO.getNombresocio() );
        socioEntity.setApellidopaternosocio( socioDTO.getApellidopaternosocio() );
        socioEntity.setApellidomaternosocio( socioDTO.getApellidomaternosocio() );
        socioEntity.setTelefono( socioDTO.getTelefono() );
        socioEntity.setDni( socioDTO.getDni() );
        socioEntity.setEstado( socioDTO.getEstado() );
        socioEntity.setTipo( socioDTO.getTipo() );
        socioEntity.setCorreo( socioDTO.getCorreo() );
        socioEntity.setToken( socioDTO.getToken() );
        socioEntity.setCorreoVerificado( socioDTO.getCorreoVerificado() );
        socioEntity.setIntentos_fallidos( socioDTO.getIntentos_fallidos() );
        socioEntity.setCuenta_bloqueada( socioDTO.getCuenta_bloqueada() );

        return socioEntity;
    }

    @Override
    public List<SocioDTO> socioDTOASocioDTO(List<SocioEntity> socios) {
        if ( socios == null ) {
            return null;
        }

        List<SocioDTO> list = new ArrayList<SocioDTO>( socios.size() );
        for ( SocioEntity socioEntity : socios ) {
            list.add( SocioEntityASocioDTO( socioEntity ) );
        }

        return list;
    }
}
