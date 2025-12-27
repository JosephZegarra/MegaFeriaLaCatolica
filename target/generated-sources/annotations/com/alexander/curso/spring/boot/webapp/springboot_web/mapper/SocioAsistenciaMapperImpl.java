package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-26T21:50:19-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class SocioAsistenciaMapperImpl implements SocioAsistenciaMapper {

    @Override
    public SocioAsistenciaDTO SocioAsistenciaEntityASocioAsistenciaDTO(SocioAsistenciaEntity socioAsistenciaEntity) {
        if ( socioAsistenciaEntity == null ) {
            return null;
        }

        SocioAsistenciaDTO socioAsistenciaDTO = new SocioAsistenciaDTO();

        socioAsistenciaDTO.setEstado( socioAsistenciaEntity.getEstado() );
        socioAsistenciaDTO.setPuntualidad( socioAsistenciaEntity.getPuntualidad() );
        socioAsistenciaDTO.setMotivo( socioAsistenciaEntity.getMotivo() );

        return socioAsistenciaDTO;
    }

    @Override
    public SocioAsistenciaEntity SocioAsistenciaDTOASocioAsistenciaEntity(SocioAsistenciaDTO socioAsistenciaDTO) {
        if ( socioAsistenciaDTO == null ) {
            return null;
        }

        SocioAsistenciaEntity socioAsistenciaEntity = new SocioAsistenciaEntity();

        socioAsistenciaEntity.setEstado( socioAsistenciaDTO.getEstado() );
        socioAsistenciaEntity.setPuntualidad( socioAsistenciaDTO.getPuntualidad() );
        socioAsistenciaEntity.setMotivo( socioAsistenciaDTO.getMotivo() );

        return socioAsistenciaEntity;
    }
}
