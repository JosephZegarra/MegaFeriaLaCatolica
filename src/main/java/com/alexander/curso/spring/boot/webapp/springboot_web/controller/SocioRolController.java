package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.RolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioRolDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.RolService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioRolService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/sociorol")
public class SocioRolController
{
    @Autowired
    private SocioService socioService;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private SocioRolService socioRolService;

    @PostMapping("/guardarrol")
    public String guardarrol(@RequestParam("numero_carnet") String numero_carnet,
                             @RequestParam("rol") String rol,
                             @RequestParam("fechainicio") LocalDate fechainicio,
                             @RequestParam("fechafin")LocalDate fechafin,
                             Model model)
    {
        //socioService.

          SocioDTO socioDTO=socioService.BuscarPorNumero_Carnet(numero_carnet);
          if(socioDTO==null)
          {
              model.addAttribute("error","El número de CARNET que ha ingresado no existe en el sistema");
              return "AsignarRol";
          }

          if(fechafin.isBefore(fechainicio))
          {
              model.addAttribute("error","La fecha final no puede ser menor que la fecha de inicio ");
              return "AsignarRol";
          }

          RolDTO rolDTO=rolService.buscarpornombre(rol);
          socioRolService.guardar(socioDTO,rolDTO,fechainicio,fechafin);




        //modificar aca
        return "MenuSecretaria";
    }
}
