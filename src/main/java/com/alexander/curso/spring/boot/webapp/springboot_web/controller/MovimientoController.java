package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller

@RequestMapping("/movimiento")
public class MovimientoController
{
    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private SocioRolRepository socioRolRepository;


    @PostMapping("/guardarmovimiento")
    public String guardarmovimiento(@RequestParam("tipo") String tipo,
                                    @RequestParam("descripcion") String descripcion,
                                    @RequestParam("monto")BigDecimal monto,
                                    @RequestParam("numero_carnet") String numero_carnet , Model model)
    {
        SocioDTO socioDTO=socioService.BuscarPorNumero_Carnet(numero_carnet);
        if(socioDTO==null)
        {
            model.addAttribute("error","El número de carnet que ha ingresado no se encuentra registrado en el sistema.");
            return "RegistroMovimiento";
        }
        else if(socioDTO.getEstado().equalsIgnoreCase("Inactivo"))
        {
            model.addAttribute("error","El socio, ya no pertenece a la asociación.");
            return "RegistroMovimiento";
        }
        else{
        System.out.println("Socio cumple con los requisitos");
        }

        String rolactivo=socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);

        if(!rolactivo.equalsIgnoreCase("Tesorera"))
        {
            model.addAttribute("error", "El socio no cumple con los requisitos, debe tener un rol actual de Tesorera.");
            return "RegistroMovimiento";
        }

        MovimientoDTO movimientoDTO= new MovimientoDTO();
        movimientoDTO.setIdsocio(socioDTO.getIdsocio());
        movimientoDTO.setTipo(tipo);
        movimientoDTO.setDescripcion(descripcion);
        movimientoDTO.setMonto(monto);
        movimientoDTO.setFecha(LocalDate.now());

        movimientoService.guardar(movimientoDTO);

        return "redirect:/movimiento/ListaMovimientos";
    }



    @GetMapping("/ListaMovimientos")
    public String ListaMovimientos(Model model)
    {
        List<MovimientoDTO> movimientos=movimientoService.listarIngresos();

        model.addAttribute("movimientos",movimientos);
        model.addAttribute("Exito", "Usted sí cumple con los requisitos de ingreso al sistema");
        return "ListaMovimientos";

    }

    @GetMapping("/ListaEgresos")
    public String ListaEgresos(Model model)
    {
        List<MovimientoDTO>movimientos=movimientoService.listarEgresos();
        model.addAttribute("movimientos",movimientos);
        return "verEgresos";
    }


}
