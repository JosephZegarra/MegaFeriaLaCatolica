package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.RolEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.RolMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.RolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    RolServiceImpl implements RolService
{
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolMapper rolMapper;

    @Override
    public RolDTO buscarpornombre(String nombre)
    {
        RolEntity rolEntity=rolRepository.findByNombrerol(nombre) .orElseThrow(() -> new RuntimeException("No se encontró el rol con nombre: " + nombre));

        return rolMapper.RolEntityARolDTO(rolEntity);
    }
}
