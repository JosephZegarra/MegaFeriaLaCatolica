package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.RolEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoMapper
{
    @Mapping(source = "socio.idsocio", target = "idsocio")
    MovimientoDTO MovimientoEntityAMovimientoDTO(MovimientoEntity movimientoEntity);

    @Mapping(source = "idsocio", target = "socio")
    MovimientoEntity MovimientoDTOAMovimientoEntity(MovimientoDTO movimientoDTO);

    // 🔹 Este método convierte el ID del socio en una entidad SocioEntity
    default SocioEntity map(Integer idsocio) {
        if (idsocio == null) {
            return null;
        }
        SocioEntity  socio = new SocioEntity();
        socio.setIdsocio(idsocio);
        return socio;
    }

}

