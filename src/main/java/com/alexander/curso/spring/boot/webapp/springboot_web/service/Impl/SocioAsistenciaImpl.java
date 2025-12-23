package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioAsistenciaMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioAsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioAsistenciaImpl implements SocioAsistenciaService
{
    @Autowired
    SocioAsistenciaRepository socioAsistenciaRepository;

    @Autowired
    SocioAsistenciaMapper socioAsistenciaMapper;

    @Override
    public SocioAsistenciaDTO guardar(SocioAsistenciaDTO socioAsistenciaDTO)
    {
        SocioAsistenciaEntity socioAsistenciaEntity=socioAsistenciaMapper.SocioAsistenciaDTOASocioAsistenciaEntity(socioAsistenciaDTO);
        SocioAsistenciaEntity savedEntity=socioAsistenciaRepository.save(socioAsistenciaEntity);

        return socioAsistenciaMapper.SocioAsistenciaEntityASocioAsistenciaDTO(savedEntity);
    }

    public boolean existsByAsistencia_Idasistencia(Integer idsocio, Integer idAsistencia)
    {
        return socioAsistenciaRepository.existsBySocio_IdsocioAndAsistencia_Idasistencia(idsocio,idAsistencia);
    }
}
