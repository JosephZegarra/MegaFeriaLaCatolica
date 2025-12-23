package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.FamiliaresRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.FamiliarService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socios")
public class FamiliarJsonController
{
    @Autowired
    private SocioService socioService;

    @Autowired
    private FamiliaresRepository familiaresRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private FamiliarService familiarService;


    @PostMapping("/guardarfamiliar")
    public ResponseEntity<?> guardarfamiliar(@RequestParam("numero_carnet") String numero_carnet, @RequestBody FamiliaresDTO request) {

        // Buscar ID Socio en base al carnet
        SocioDTO socioDTO = socioService.BuscarPorNumero_Carnet(numero_carnet);

        if (socioDTO == null) {
            return ResponseEntity.badRequest().body("El número de carnet no está registrado.");
        }

        Integer idsocio = socioDTO.getIdsocio();

        // Validar que no tenga sucesor
        if (familiaresRepository.existsBySocio_Idsocio(idsocio)) {
            return ResponseEntity.badRequest().body("Este socio ya tiene un sucesor registrado.");
        }

        // Validar si DNI ya existe en familiares
        if (familiaresRepository.existsByDni(request.getDni())) {
            return ResponseEntity.badRequest().body("Ya existe un familiar con este DNI.");
        }

        // Validar si DNI ya existe en socios
        if (socioRepository.existsByDni(request.getDni())) {
            return ResponseEntity.badRequest().body("El DNI pertenece a un socio registrado.");
        }

        // Crear objeto para guardar
        FamiliaresDTO sociofamiliar = new FamiliaresDTO();
        sociofamiliar.setIdsocio(idsocio);
        sociofamiliar.setNombrefamiliar(request.getNombrefamiliar());
        sociofamiliar.setApellidopaternofamiliar(request.getApellidopaternofamiliar());
        sociofamiliar.setApellidomaternofamiliar(request.getApellidomaternofamiliar());
        sociofamiliar.setParentesco(request.getParentesco());
        sociofamiliar.setTelefono(request.getTelefono());
        sociofamiliar.setDni(request.getDni());
        sociofamiliar.setEssocio(request.isEssocio());

        // Guardar
        familiarService.guardar(sociofamiliar);

        return ResponseEntity.ok("Familiar registrado correctamente");
    }

}
