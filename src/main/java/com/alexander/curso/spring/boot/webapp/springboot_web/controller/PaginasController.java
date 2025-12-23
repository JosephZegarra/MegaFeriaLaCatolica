package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.PagosDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/paginas")
public class PaginasController {
    @Autowired
    private SocioService socioService;

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DeudaService deudaService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private PagosService pagosService;

    @Autowired
    private DashboardService dashboardService;



    @GetMapping("/infoUsuario")
    public String infoUsuario(HttpSession session, Model model) {
        SocioDTO socio = (SocioDTO) session.getAttribute("socioLogeado");
        if (socio == null) {
            model.addAttribute("error", "Debe iniciar sesión para mostrar los datos de su cuenta.");
            return "Login";
        }
        model.addAttribute("socio", socio);
        return "DetalleSocio";
    }

    /*
    @GetMapping("/verListaReunion")
    public String verListaReunion(Model model)
    {

    }
    */

    @GetMapping("/Login")
    public String Login() {
        return "Login";
    }

    @GetMapping("/verHistorialPagos")
    public String verHistorialPagos(HttpSession session, Model model) {
        SocioDTO socio = (SocioDTO) session.getAttribute("socioLogeado");
        List<PagosDTO> pagos = pagosService.obtenerPagosPorSocio(socio.getIdsocio());
        model.addAttribute("pagos", pagos);

        //model.addAttribute("socio", socio);
        return "HistorialPagos";
    }

    @GetMapping("/verDeudas")
    public String verDeudas(HttpSession session, Model model) {
        //extraigo datos del socio
        SocioDTO socio = (SocioDTO) session.getAttribute("socioLogeado");
        //Verificando si extraigo los datos del socio
        if (socio == null) {
            model.addAttribute("error", "La sesión ha expirado. Inicie sesión de nuevo.");
            return "Login";
        }

        //redireccionando al metodo para saber su deuda
        return "redirect:/deudas/deudasocio?numero_carnet=" + socio.getNumero_carnet();

    }

    /*
    @GetMapping("/verListaReuniones")
    public String verListaReuniones()
    {

    }
    */


    @GetMapping("/registrarmovimiento")
    public String registrarmovimiento() {
        return "RegistroMovimiento";
    }


    @GetMapping("/verEgresos")
    public String verEgresos(Model model) {
        //List<MovimientoDTO>movimientos=movimientoService.listarEgresos();
        //model.addAttribute("movimientos",movimientos);
        return "redirect:/movimiento/ListaEgresos";
    }

    @GetMapping("/RegistroSocio")
    public String RegistroSocio() {
        return "RegistroSocio";
    }

    @GetMapping("/RegistroFamiliaresSocio")
    public String RegistroFamiliaresSocio() {
        return "RegistroFamiliaresSocio";
    }

    @GetMapping("/asistencias")
    public String asistencia(Model model) {
        List<SocioDTO> socios = socioService.listar();//Le envio la lista de socios
        model.addAttribute("socios", socios);
        //CREO una asistencia
        //AsistenciaDTO nuevaasistencia = new AsistenciaDTO();
        //nuevaasistencia.setFecha(LocalDateTime.now());
        //nuevaasistencia.setTipoevento("Asistencia");
        //nuevaasistencia.setDescripcion("Asistencia a los socios todos los días");
        //AsistenciaDTO asistenciaGuardada = asistenciaService.guardarasistenciatabla(nuevaasistencia);
        //Integer idAsistencia=nuevaasistencia.getIdasistencia();
        //model.addAttribute("idAsistencia",idAsistencia);
        //model.addAttribute("idAsistencia", asistenciaGuardada.getIdasistencia());
        return "ModuloAsistencia";

    }







    @GetMapping("/crearReunion")
    public String crearReunion(Model model)
    {
       return "CrearReunion";
    }

    @GetMapping("/realizarpago")
    public String realizarpago(@RequestParam Integer idsocio,
                               @RequestParam(required = false) BigDecimal totalDeuda,
                               @RequestParam(required = false) String numero_carnet, Model model)
    {
        SocioDTO socioDTO=socioService.BuscarPorId(idsocio);

        //Si no llega la deuda por parametro , la obtendremos del servicio
        if(totalDeuda==null)
        {
             totalDeuda=deudaService.obtenerdeudaporsocio(socioDTO.getIdsocio());
        }
        model.addAttribute("socio",socioDTO);
        model.addAttribute("totalDeuda",totalDeuda);
        model.addAttribute("numero_carnet",numero_carnet);

        return "RealizarPago";
    }

    @GetMapping("/paginaspago")
    public String paginaspago(Model model){ return "ConsultarDeuda";}

    @GetMapping("/asignarrol")
    public String asignarrol(Model model)
    {
        return "AsignarRol";
    }

    @GetMapping("/menusocio")
    public String menusocio(HttpSession session, Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        model.addAttribute("socio",socio);
        return "MenuSocio";
    }


    @GetMapping("/presidente")
    public String presidente(HttpSession session, Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        //SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        //model.addAttribute("socio",socio);
        //model.addAttribute("dashboardData",dashboardService.getDashboardData());
        return "redirect:/Dashboard/dashboard";
    }
    @GetMapping("/menusecretaria")
    public String menusecretaria(HttpSession session, Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        model.addAttribute("socio",socio);
        return "MenuSecretaria";
    }

    @GetMapping("/delegadoporrubro")
    public String delegadoporrubro(HttpSession session, Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        model.addAttribute("socio",socio);
        return "MenuDelegadoPorRubro";
    }

    @GetMapping("/tesorera")
    public String tesorera(HttpSession session,Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        model.addAttribute("socio",socio);

        return "redirect:/movimiento/ListaMovimientos";

    }

    @GetMapping("/dirigente")
    public String dirigente(HttpSession session,Model model)
    {
        String token=(String) session.getAttribute("jwtToken");

        if(token ==null || !jwtUtil.validateToken(token))
        {
            model.addAttribute("error","Sesión expirada o no válida. Inicie sesión nuevamente.");
            return "Login";
        }
        SocioDTO socio=(SocioDTO) session.getAttribute("socioLogeado");
        model.addAttribute("socio",socio);

        return "MenuDirigente";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model)
    {   //elimina toda la sesión todos los atribuos token, socioLogeadoo, etc
        session.invalidate();
        //mensaje opcional
        model.addAttribute("mensaje", "Sesión cerrada correctamente.");
        return "Login";
    }

    //mETODO QUE pasa toda la información a la página Realizar pago para que muestre en Relizar pago
    //el total pagos  y el saldo pendiente.
    @GetMapping("/paginaRegresarRealizarPago")
    public String paginaRegresarRealizarPago( @RequestParam("iddeuda") Integer iddeuda, Model model)
    {

        DeudaEntity deuda = deudaService.obtenerDeudaPorId(iddeuda);
        if (deuda == null) {
            model.addAttribute("error", "No se encontró la deuda seleccionada");
            return "ConsultarDeuda";
        }
        BigDecimal totalPagos = deuda.getPagos().stream()
                .map(p -> p.getMonto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoPendiente = deuda.getMonto().subtract(totalPagos);
        SocioDTO socioDTO = deudaService.obtenerSocioPorIdDeuda(iddeuda);

        model.addAttribute("deuda", deuda);
        model.addAttribute("iddeuda", deuda.getIddeuda());
        model.addAttribute("saldoPendiente", saldoPendiente);
        model.addAttribute("numero_carnet", socioDTO.getNumero_carnet());

        return "RealizarPago";

    }


    /*
    @GetMapping("/ListaMoovimientos")
    public String ListaMovimientos()

*/




}

