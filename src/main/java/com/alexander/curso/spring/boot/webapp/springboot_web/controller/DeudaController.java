package com.alexander.curso.spring.boot.webapp.springboot_web.controller;


import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DeudaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deudas")
public class DeudaController
{
    @Autowired
    private SocioService socioService;

    @Autowired
    private DeudaService deudaService;

    @Autowired
    private SocioRolRepository socioRolRepository;


    @GetMapping("/deudasocio")
    public String obtenerdeudasocio(@RequestParam("numero_carnet") String numero_carnet, Model model)
    {
        SocioDTO socioDTO=socioService.BuscarPorNumero_Carnet(numero_carnet);

        if(socioDTO==null)
        {
            model.addAttribute("error","No se encontró ningun socio con ese número de carnet");
            return "ConsultarDeuda";
        }

        String rolactual=socioRolRepository.EncuentraRolActivoPorSocioId(socioDTO.getNumero_carnet());

        System.out.println("ROL ACTUAL = " + rolactual);
        /*
        if ("Activo".equalsIgnoreCase(socioDTO.getEstado()) && rolactual == null) {
            model.addAttribute("error", "No tiene un cargo asignado, solo puede ingresar como Socio.");
            return "Login";
        }
        */




        //Obtener la deuda Pendiente por socio
        List<DeudaEntity> deudasPendientes=deudaService.obtenerDeudaPendientePorSocio(socioDTO.getIdsocio());

        if(deudasPendientes==null || deudasPendientes.isEmpty())
        {   /*
            if(rolactual.equalsIgnoreCase("Secretaria")) {
                model.addAttribute("exito", "El socio no tiene deudas registradas");
                return "ConsultarDeuda";
            }
            else {
                model.addAttribute("exito", "El socio no tiene deudas registradas");
                return "ListaDeudasSocio";
            }
            */
            model.addAttribute("exito", "El socio no tiene deudas registradas");
            return "ListaDeudasSocio";
        }

        //Creamos una lista auxiliar con los saldos pendientes calculados
        List<Map<String, Object>> deudasConSaldo=new ArrayList<>();

        //Cada deuda le resta lo que pago , las deudas se pagan individualmente 
        for(DeudaEntity deuda:deudasPendientes)
        {
            BigDecimal totalPagado = deuda.getPagos().stream()
                    .map(PagosEntity::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal saldoPendiente=deuda.getMonto().subtract(totalPagado);

            //aseguramos que nunca salga negatico   
            if(saldoPendiente.compareTo(BigDecimal.ZERO)<0){
                saldoPendiente=BigDecimal.ZERO;
            }

            Map<String,Object>data=new HashMap<>();
            data.put("iddeuda",deuda.getIddeuda());
            data.put("estado",deuda.getEstado());
            data.put("montooriginal", deuda.getMonto());
            data.put("saldopendiente",saldoPendiente);
            data.put("fecha", deuda.getFecha());
            deudasConSaldo.add(data);
        }

        BigDecimal totaldeuda=deudaService.obtenerdeudaporsocio(socioDTO.getIdsocio());

        //Lo que le paso
        model.addAttribute("socio", socioDTO);
        //model.addAttribute("iddeuda",deudaPendiente.getIddeuda());
        //model.addAttribute("deudas",deudasPendientes);
        model.addAttribute("deudas",deudasConSaldo);
        model.addAttribute("totalDeuda",totaldeuda);
        // Viendo rol actual del socios

        return "ListaDeudasSocio";
        /*
        if(rolactual==null)//si es socio va aca
        {
            return "ListaDeudasSocio";
        }
        else{
            return "ListaDeudasSocio";
        }
        */

        /*
        else if (rolactual.equalsIgnoreCase("Secretaria")){
            return "ConsultarDeuda";
        }
        */



    }



    @GetMapping("/deudacobrarsocio")
    public String deudacobrarsocio(@RequestParam("numero_carnet") String numero_carnet, Model model)
    {
        SocioDTO socioDTO=socioService.BuscarPorNumero_Carnet(numero_carnet);

        if(socioDTO==null)
        {
            model.addAttribute("error","No se encontró ningun socio con ese número de carnet");
            return "ConsultarDeuda";
        }

        String rolactual=socioRolRepository.EncuentraRolActivoPorSocioId(socioDTO.getNumero_carnet());

        System.out.println("ROL ACTUAL = " + rolactual);
            /*
            if ("Activo".equalsIgnoreCase(socioDTO.getEstado()) && rolactual == null) {
                model.addAttribute("error", "No tiene un cargo asignado, solo puede ingresar como Socio.");
                return "Login";
            }
            */




        //Obtener la deuda Pendiente por socio
        List<DeudaEntity> deudasPendientes=deudaService.obtenerDeudaPendientePorSocio(socioDTO.getIdsocio());

        if(deudasPendientes==null || deudasPendientes.isEmpty())
        {   /*
            if(rolactual.equalsIgnoreCase("Secretaria")) {
                model.addAttribute("exito", "El socio no tiene deudas registradas");
                return "ConsultarDeuda";
            }
            else {
                model.addAttribute("exito", "El socio no tiene deudas registradas");
                return "ListaDeudasSocio";
            }
            */
            model.addAttribute("exito", "El socio no tiene deudas registradas");
            return "ConsultarDeuda";
        }

        //Creamos una lista auxiliar con los saldos pendientes calculados
        List<Map<String, Object>> deudasConSaldo=new ArrayList<>();

        //Cada deuda le resta lo que pago , las deudas se pagan individualmente
        for(DeudaEntity deuda:deudasPendientes)
        {
            BigDecimal totalPagado = deuda.getPagos().stream()
                    .map(PagosEntity::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal saldoPendiente=deuda.getMonto().subtract(totalPagado);

            //aseguramos que nunca salga negatico
            if(saldoPendiente.compareTo(BigDecimal.ZERO)<0){
                saldoPendiente=BigDecimal.ZERO;
            }

            Map<String,Object>data=new HashMap<>();
            data.put("iddeuda",deuda.getIddeuda());
            data.put("estado",deuda.getEstado());
            data.put("montooriginal", deuda.getMonto());
            data.put("saldopendiente",saldoPendiente);
            data.put("fecha", deuda.getFecha());
            deudasConSaldo.add(data);
        }

        BigDecimal totaldeuda=deudaService.obtenerdeudaporsocio(socioDTO.getIdsocio());

        //Lo que le paso
        model.addAttribute("socio", socioDTO);
        //model.addAttribute("iddeuda",deudaPendiente.getIddeuda());
        //model.addAttribute("deudas",deudasPendientes);
        model.addAttribute("deudas",deudasConSaldo);
        model.addAttribute("totalDeuda",totaldeuda);
        // Viendo rol actual del socios

        return "ConsultarDeuda";


        /*
        if(rolactual==null)
        {
            return "ListaDeudasSocio";
        }
        else if (rolactual.equalsIgnoreCase("Secretaria")){
            return "ConsultarDeuda";
        }
        else{
            return "ListaDeudasSocio";
        }

         */

    }

}
