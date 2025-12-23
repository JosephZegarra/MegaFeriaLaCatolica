package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DeudaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioRolService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController
{
    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private DeudaService deudaService;

    @Autowired
    private SocioRolService socioRolService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private SocioRolRepository socioRolRepository;

    @PostMapping("/guardar")
    public String guardarAsistencia(
            @RequestParam("idsSocios") List<Integer> idsSocios,
            @RequestParam("estados") List<String> estados,
            @RequestParam("puntualidades") List<String> puntualidades,
            @RequestParam("motivos") List<String> motivos,
            @RequestParam(value="idAsistencia", required = false) Integer idAsistencia, Model model,HttpSession session
    ) {
        //Extraigo el dato del socio
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        String rol=socioRolRepository.EncuentraRolActivoPorSocioId(socio.getNumero_carnet());

        Integer idAsistenciaFinal;

        if(idAsistencia!=null)
        {
            idAsistenciaFinal=idAsistencia;
        }
        else {
            //Pero antes Mejor creo la asistencia
            AsistenciaDTO nuevaasistencia = new AsistenciaDTO();
            nuevaasistencia.setFecha(LocalDateTime.now());
            nuevaasistencia.setTipoevento("Asistencia");
            nuevaasistencia.setDescripcion("Asistencia a los socios todos los días");

            //Guardar y obtener el ID real generado de la base de datos
            AsistenciaDTO asistenciaGuardada = asistenciaService.guardarasistenciatabla(nuevaasistencia);
            idAsistenciaFinal = asistenciaGuardada.getIdasistencia();
        }

            //model.addAttribute("idAsistencia", asistenciaGuardada.getIdasistencia());
            //Guardo la asistencia de los socios
        List<SocioAsistenciaEntity>listasocioasistencia= asistenciaService.guardarasistenciasocios(idAsistenciaFinal,idsSocios,estados,puntualidades,motivos);
            //llenar tabla deudas
        deudaService.generardeudaporasistencia(listasocioasistencia);
        model.addAttribute("idAsistencia",idAsistenciaFinal);
       // model.addAttribute("Exito", "Se registró correctamente la asistencia");

        return "MenuDelegadoPorRubro";
    }

    @PostMapping("/asistenciasPorReunion")
    public String asistenciasPorReunion(@RequestParam("idasistencia") Integer idAsistencia, @RequestParam("fecha") @DateTimeFormat(iso  = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fechareunion,
                                        Model model, HttpSession session) {


        // 1. Verificar si la reunión ya tiene asistencia registrada
        AsistenciaDTO asistenciaExistente = asistenciaService.obtenerPorId(idAsistencia);

       // AsistenciaDTO asistencia;

        if (asistenciaExistente == null) {

            model.addAttribute("error", "La reunión no existe");
            return "ListaReuniones";

        }

        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        //Validar si es hora de tomar asistenncia
        //(LE ESTOY DICIENDO SI EL DELEGADO POR RUBRO FUE A TAL REUNION , cuenta como si la asistencia fuera tomada
        boolean yaTomada=asistenciaService.existeAsistenciaParaDelegado(socio.getNumero_carnet(),idAsistencia );
        if(yaTomada)
        {
            model.addAttribute("error","Ya se tomó la asistencia de esta reunión ");
            return "MenuDelegadoPorRubro";
        }

        //no permitir antes de la reunion tomar asistencia
        LocalDateTime ahora=LocalDateTime.now();
        LocalDateTime limite=fechareunion.plusMinutes(30);


        if(ahora.isBefore(fechareunion))
        {
            model.addAttribute("error","No se puede tomar asistencia antes de la fecha y hora programada "+ fechareunion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) );
            return "MenuDelegadoPorRubro";
        }

        if(ahora.isAfter(limite))
        {
            model.addAttribute("error","La reunión ya superó el tiempo límite de asistencia (30 min).");
            return "MenuDelegadoPorRubro";
        }

        // 3. Enviar socios + id de asistencia para tomar asistencia
        List<SocioDTO> socios = socioService.listar();
        model.addAttribute("socios", socios);
        model.addAttribute("idAsistencia",idAsistencia);

        return "ModuloAsistencia";
    }

    @GetMapping("/reunionesPendientes")
    public String listarReunionesPendientes(@RequestParam("numeroCarnet") String numeroCarnet, Model model) {
        List<AsistenciaDTO> todasReuniones = asistenciaService.listarTodas();

        // Filtrar reuniones donde el delegado aún no tomó asistencia
        List<AsistenciaDTO> reunionesPendientes = todasReuniones.stream()
                .filter(r -> !asistenciaService.existeAsistenciaParaDelegado(numeroCarnet, r.getIdasistencia()))
                .collect(Collectors.toList());

        model.addAttribute("reunionesPendientes", reunionesPendientes);
        return "ListaReunionesPendientes"; // plantilla Thymeleaf
    }

    /*
    @GetMapping("/asistencia/resumen")
    public List<ResumenAsistenciaDTO> obtenerResumen() {
        LocalDateTime from = LocalDate.now().minusDays(6).atStartOfDay();
        LocalDateTime to = LocalDate.now().plusDays(1).atStartOfDay();

        return asistenciaService.obtenerResumenPorFecha(from, to);
    }*/
    @GetMapping("/asistencia/resumen")
    public List<ResumenAsistenciaDTO> obtenerResumen() {
         LocalDate from = LocalDate.now().minusDays(6); // hace 6 días
        LocalDate to = LocalDate.now();               // hoy

       // LocalDateTime from=fromDate.atStartOfDay();
        //LocalDateTime to=toDate.plusDays(1).atStartOfDay();
        return asistenciaService.obtenerResumenPorFecha(from, to);
    }






}
