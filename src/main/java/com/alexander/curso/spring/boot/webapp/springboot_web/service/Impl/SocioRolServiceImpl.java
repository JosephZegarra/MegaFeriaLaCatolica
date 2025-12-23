package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.RolEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioRolEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.RolMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioRolMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioRolService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SocioRolServiceImpl implements SocioRolService
{
    @Autowired
    SocioRolRepository socioRolRepository;

    @Autowired
    SocioRolMapper socioRolMapper;

    @Autowired
    SocioMapper socioMapper;

    @Autowired
    RolMapper rolMapper;



    @Override
    public void guardar(SocioDTO socioDTO,RolDTO rolDTO,LocalDate fechainicio,LocalDate fechafin)
    {
        SocioEntity socioEntity=socioMapper.SocioDTOASocioEntity(socioDTO);
        RolEntity rolEntity=rolMapper.RolDTOARolEntity(rolDTO);

        SocioRolEntity socioRolEntity =new SocioRolEntity();
        socioRolEntity.setSocio(socioEntity);
        socioRolEntity.setRol(rolEntity);
        socioRolEntity.setFechainicio(fechainicio);
        socioRolEntity.setFechafin(fechafin);

        SocioRolEntity savedEntity=socioRolRepository.save(socioRolEntity);


    }

}
