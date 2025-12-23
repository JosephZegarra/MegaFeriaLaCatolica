package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.AsistenciaMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.AsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reunion")
public class ReunionController
{
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private AsistenciaMapper asistenciaMapper;

    @Autowired
    private AsistenciaService asistenciaService;


    @PostMapping("/guardarReunion")
    public String guardarReunion(
            @RequestParam("descripcion") String descripcion,
            @RequestParam("tipoevento") String tipoevento,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fecha , Model model
    ) {
        if(fecha==null)
        {
            model.addAttribute("error","Debe seleccionar la fecha y hora de la reunión");
            return "CrearReunion";
        }

        //asistenciaService.existeAsistenciaParaDelegado()

        AsistenciaEntity reunion = new AsistenciaEntity();
        reunion.setDescripcion(descripcion);
        reunion.setTipoevento(tipoevento); // "Reunion"
        //convertir de Local date a LocalDateTime
        //reunion.setFecha(fecha.atStartOfDay());
        reunion.setFecha(fecha);

        asistenciaRepository.save(reunion);
        model.addAttribute("Exito","Se registro correctamente la reunión");
        return "CrearReunion";
    }




    @GetMapping("/verListaReuniones")
    public String verListaReuniones(Model model, HttpSession session)
    {
        //Obtener el socio logeado
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        if(socio==null)
        {
            model.addAttribute("error","El usuario no cumple los requisitos para ver tomar asistencia");
            return "Login";

        }


        //obtengo todas las reuniones cuyo tipo No sea Asistencia
        List<AsistenciaEntity>reuniones=asistenciaRepository.findByTipoeventoNot("Asistencia");

        //convertir a DTO
        List<AsistenciaDTO>reunionesDTO=asistenciaMapper.asistenciaEntityListAAsistenciaDTO(reuniones);
        //filtrar reuniones que el delegado aún no ha tomado

        //Fecha ahora
        LocalDateTime ahora=LocalDateTime.now();

        List<AsistenciaDTO>reunionesPendientes=reunionesDTO.stream()
                .filter(r->!asistenciaService.existeAsistenciaParaDelegado(socio.getNumero_carnet(),r.getIdasistencia()
                ) ).filter(r->!r.getFecha().isBefore(ahora)).collect(Collectors.toList());//todas las fechas que superan a la fecha actual

        //ENVIAR LISTA FILTRADA A LA VISTA
        reunionesDTO.stream().forEach(r -> {
            System.out.println("ID DTO=>"+r.getIdasistencia()+"DTO fechas => " + r.getFecha());
        });
        model.addAttribute( "listareuniones",reunionesPendientes);
        return "ListaReuniones";

    }

    @GetMapping("/verListaReunionesSocio")
    public String verListaReunionesSocio(Model model, HttpSession session)
    {
        //Obtener el socio logeado
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        if(socio==null)
        {
            model.addAttribute("error","El usuario no cumple los requisitos para ver tomar asistencia");
            return "Login";

        }

        //Fecha actual
        LocalDateTime ahora=LocalDateTime.now();
        //obtengo todas las reuniones cuyo tipo No sea Asistencia
        List<AsistenciaEntity>reuniones=asistenciaRepository.findByTipoeventoNot("Asistencia");

        //convertir a DTO
        List<AsistenciaDTO>reunionesDTO=asistenciaMapper.asistenciaEntityListAAsistenciaDTO(reuniones);
        //filtrar reuniones que el delegado aún no ha tomado

        List<AsistenciaDTO>reunionesPendientes=reunionesDTO.stream()
                .filter(r->r.getFecha().isAfter(ahora))
                .filter(r->!asistenciaService.existeAsistenciaParaDelegado(socio.getNumero_carnet(),r.getIdasistencia()
                ) ).collect(Collectors.toList());

        //ENVIAR LISTA FILTRADA A LA VISTA
        reunionesDTO.stream().forEach(r -> {
            System.out.println("ID DTO=>"+r.getIdasistencia()+"DTO fechas => " + r.getFecha());
        });
        model.addAttribute( "listareuniones",reunionesPendientes);
        return "ListaReunionesUsuario";

    }





}
