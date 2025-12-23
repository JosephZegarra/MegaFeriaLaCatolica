package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.FamiliaresRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.FamiliarService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/familiar")
public class FamiliarViewController
{
    @Autowired
    private FamiliarService familiarService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private FamiliaresRepository familiaresRepository;

    @PostMapping("/guardarfamiliar")
    public String guardarfamiliar(@RequestParam("nombrefamiliar") String nombrefamiliar,
                                  @RequestParam("apellidopaternofamiliar") String apellidopaternofamiliar,
                                  @RequestParam("apellidomaternofamiliar") String apellidomaternofamiliar,
                                  @RequestParam("parentesco") String parentesco,
                                  @RequestParam("telefono") String telefono,
                                  @RequestParam("dni")String dni,
                                  @RequestParam("essocio") boolean essocio,
                                  @RequestParam("numero_carnet") String numero_carnet, Model model
    )
    {
        //Buscando el ID Socio en base al carnet
        //Integer idsocio=socioService.obtenerIdSocioPorCarnet(numero_carnet);
        SocioDTO socioDTO=socioService.BuscarPorNumero_Carnet(numero_carnet);
        Integer idsocio=socioDTO.getIdsocio();
        //System.out.println(socioDTO.getIdsocio());
        if(socioDTO==null)
        {
            model.addAttribute("error","El número de carnet que ha ingresado no se encuentra registrado en el sistema.");
            return "RegistroFamiliaresSocio";
        }

        if(familiaresRepository.existsBySocio_Idsocio(idsocio))
        {

            model.addAttribute("error", "No es posible registrar otro sucesor: este socio ya tiene un sucesor registrado en la Asociación.");
            return "RegistroFamiliaresSocio";

        }
        // Verificar si el DNI ya existe en familiares
        if (familiaresRepository.existsByDni(dni)) {
            model.addAttribute("error", "Ya existe un familiar registrado con este DNI.");
            return "RegistroFamiliaresSocio";
        }

// Verificar si el DNI ya existe en socios
        if (socioRepository.existsByDni(dni)) {
            model.addAttribute("error", "El DNI ingresado ya pertenece a un socio registrado.");
            return "RegistroFamiliaresSocio";
        }



        //System.out.println(correo);
        FamiliaresDTO sociofamiliar = new FamiliaresDTO();
        sociofamiliar.setIdsocio(socioDTO.getIdsocio());


        sociofamiliar.setNombrefamiliar(nombrefamiliar);
        sociofamiliar.setApellidopaternofamiliar(apellidopaternofamiliar);
        sociofamiliar.setApellidomaternofamiliar(apellidomaternofamiliar);
        sociofamiliar.setParentesco(parentesco);
        sociofamiliar.setTelefono(telefono);
        sociofamiliar.setDni(dni);
        sociofamiliar.setEssocio(essocio);

        familiarService.guardar(sociofamiliar);
        //socioService.guardar(socio1);

        return "redirect:/socios/lista";
    }
}
