package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.FamiliaresEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.FamiliarMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.FamiliaresRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamiliarServiceImpl implements FamiliarService
{
   @Autowired
   FamiliaresRepository familiaresRepository;

   @Autowired
    FamiliarMapper familiarMapper;

   @Override
   public FamiliaresDTO guardar(FamiliaresDTO familiaresDTO)
   {
       //Convertir de DTO a entity
       FamiliaresEntity familiaresEntity= familiarMapper.FamiliaresDTOAFamiliaresEntity(familiaresDTO);
       FamiliaresEntity savedEntity=familiaresRepository.save(familiaresEntity);
       //Guardar la entoidad
       //System.out.println(socioDTO.getCorreo());
       //System.out.println(socioEntity.getCorreo());

       return familiarMapper.FamiliaresEntityAFamiliaresDTO(savedEntity);
   }


}
