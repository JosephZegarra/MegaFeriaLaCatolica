package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DeudaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.PagosService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/pagos")
public class PagosController
{
    @Autowired
    private PagosService pagosService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private DeudaService deudaService;


     //metodo qe guarda en la base de datos el pago que se hizo
    @PostMapping("/registrarPago")
    public String registrarPago(HttpSession session,@RequestParam("iddeuda") Integer iddeuda,
                                @RequestParam("monto")BigDecimal monto, RedirectAttributes redirectAttributes)
    {
        SocioDTO socioDTO=(SocioDTO) session.getAttribute("socioLogeado");
        try{
            pagosService.registrarpago(iddeuda,monto,socioDTO.getNumero_carnet());
            //redirectAttributes.addFlashAttribute("mensaje","Pago registrado correctamente");
        }catch (RuntimeException e)
        {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            //return "RealizarPago";
            redirectAttributes.addAttribute("iddeuda", iddeuda);
            return "redirect:/paginas/paginaRegresarRealizarPago";
        }

        //return "MenuSecretaria";
        return "ConsultarDeuda";
    }

    //mETODO QUE PASA TODA LA INFORMACIÓN QUE DEBE EL USUARIO

    @GetMapping("/realizarpago")
    public String realizarPago(@RequestParam("iddeuda") Integer iddeuda, Model model) {
        DeudaEntity deuda = deudaService.obtenerDeudaPorId(iddeuda);

        if (deuda == null) {
            model.addAttribute("error", "No se encontró la deuda seleccionada");
            return "ConsultarDeuda";
        }

        BigDecimal totalPagos = deuda.getPagos().stream()
                .map(p -> p.getMonto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoPendiente = deuda.getMonto().subtract(totalPagos);
        //busco el socio de la deuda //PONER DTO
        SocioDTO socioDTO=deudaService.obtenerSocioPorIdDeuda(iddeuda);



        model.addAttribute("deuda", deuda);
        model.addAttribute("iddeuda", deuda.getIddeuda());
        model.addAttribute("saldoPendiente", saldoPendiente);
        model.addAttribute("numero_carnet", socioDTO.getNumero_carnet());


        return "RealizarPago";
    }









}
