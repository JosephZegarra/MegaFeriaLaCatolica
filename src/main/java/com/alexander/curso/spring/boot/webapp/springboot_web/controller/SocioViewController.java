package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/socios")
public class SocioViewController
{
    @Autowired
    private SocioRolRepository socioRolRepository;
    @Autowired
    private SocioService socioService;

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private SocioMapper socioMapper;

    @GetMapping("/lista")
    public String listarSocios(Model model)
    {
        List<SocioDTO>socios=socioService.listar();
        model.addAttribute("socios", socios);
        return "ListaSocios";

    }

    @PostMapping("/ver")
    public String verSocio(@RequestParam Integer idsocio,Model model)
    {
        SocioDTO socio = socioService.BuscarPorId(idsocio);
        model.addAttribute("socio", socio);
        return "DetalleSocio";
    }


    //Metodo que sirve para poder extraer los datos de los socios y pasarlo
    //A un nuevo formulario para editar los datos
    @PostMapping("/editar")
    public String editarSocioForm(@RequestParam Integer idsocio, Model model) {
        SocioDTO socio = socioService.BuscarPorId(idsocio);
        model.addAttribute("socio", socio);
        return "EditarDatosSocios"; // nueva plantilla con el formulario de edición
    }

    //Metodo que permite editar los datos corectamete
    @PostMapping("/editarsocio") //EN EL POST TENER CUIDADO CON EL METODO DEBE SER IGUAL LA RUTA Y FUNCION
    public String editarsocio(SocioDTO socio) {
        // Aquí llamas al servicio para actualizar el socio
        socioService.editar(socio); //
        return "redirect:/socios/lista"; // redirige a la lista después de guardar

    }

    @PostMapping("/borrar")
    public String borrarSocio(@RequestParam Integer idsocio) {
        socioService.borrar(idsocio);
        return "redirect:/socios/lista";
    }

    @PostMapping("/buscarsocio")
    public String buscarsocio(
            @RequestParam("numero_carnet") String numero_carnet,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("rol") String rol,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session,
            HttpServletResponse response) {

        SocioEntity socioEntity = socioRepository.findByNumeroCarnet(numero_carnet).orElse(null);

        if (socioEntity == null) {
            model.addAttribute("error", "Número de carnet o contraseña incorrecto ");
            return "Login";
        }

        // 3) Si la cuenta está bloqueada

        if (Boolean.TRUE.equals(socioEntity.getCuenta_bloqueada())) {
            LocalDateTime fechabloqueo=socioEntity.getFecha_bloqueo();
            if(fechabloqueo!=null){
                long minutosTranscurridos= Duration.between(fechabloqueo,LocalDateTime.now()).toMinutes();
                if (minutosTranscurridos >= 5) {
                    // Desbloqueo automático
                    socioEntity.setCuenta_bloqueada(false);
                    socioEntity.setIntentos_fallidos(0);
                    socioEntity.setFecha_bloqueo(null);
                    socioRepository.save(socioEntity);
                } else {
                    model.addAttribute("error",
                            "Cuenta bloqueada. Intente nuevamente en " + (5 - minutosTranscurridos) + " minutos.");
                    return "Login";
                }
            } else {
                model.addAttribute("error", "Su cuenta está bloqueada por demasiados intentos fallidos.");
                return "Login";
            }


        }



        // 4) Validar contraseña
        SocioDTO socioDTO = socioService.validarSocio(numero_carnet, contrasena);

        if (socioDTO == null) {
            // contraseña incorrecta
            int intentos = socioEntity.getIntentos_fallidos() == null ? 0 : socioEntity.getIntentos_fallidos();
            intentos++;
            socioEntity.setIntentos_fallidos(intentos);

            if (intentos >= 5) {
                socioEntity.setCuenta_bloqueada(true);
                socioEntity.setFecha_bloqueo(LocalDateTime.now());
                socioRepository.save(socioEntity);
                model.addAttribute("error", "La cuenta está bloqueada por demasiados intentos fallidos");
                return "Login";
            }

            socioRepository.save(socioEntity);
            model.addAttribute("error", "Número de carnet o contraseña incorrecto");
            return "Login";
        }

        // 5) Si la contraseña es correcta, resetear intentos
        socioEntity.setIntentos_fallidos(0);
        socioEntity.setCuenta_bloqueada(false);
        socioEntity.setFecha_bloqueo(null);
        socioRepository.save(socioEntity);

        // 6) Convertir a DTO para usarlo en pantalla
        SocioDTO socio = socioMapper.SocioEntityASocioDTO(socioEntity);
        model.addAttribute("socio", socio);

        // 7) Verificar estado
        if ("Inactivo".equalsIgnoreCase(socio.getEstado())) {
            model.addAttribute("error", "El socio ya no pertenece a la asociación");
            return "Login";
        }

        // 8) Obtener rol activo
        //String rolActivo = socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);//Titular
        //nuevo codigo
        String rolActivo = socioRolRepository.EncontrarRolParaLogin(numero_carnet);



        if ("Activo".equalsIgnoreCase(socio.getEstado()) && rolActivo == null) {
            if (!"Socio".equalsIgnoreCase(rol)) {
                model.addAttribute("error", "No tiene un cargo asignado, solo puede ingresar como Socio.");
                return "Login";
            }
            return loginExitoso(session, socio, response, redirectAttributes, "/paginas/menusocio");
        }

        if (!rolActivo.equalsIgnoreCase(rol)) {
            model.addAttribute("error", "El rol seleccionado no coincide con el rol registrado en la asociación.");
            return "Login";
        }

        // 9) Redirigir por rol
        switch (rolActivo) {
            case "Presidente":
                return loginExitoso(session, socio, response, redirectAttributes, "/paginas/presidente");
            case "Secretaria":
                return loginExitoso(session, socio, response, redirectAttributes, "/paginas/menusecretaria");
            case "Tesorera":
                return loginExitoso(session, socio, response, redirectAttributes, "/paginas/tesorera");
            case "Delegadoporrubro":
                return loginExitoso(session, socio, response, redirectAttributes, "/paginas/delegadoporrubro");
            case "Dirigente":
                return loginExitoso(session, socio, response, redirectAttributes, "/paginas/dirigente");
            default:
                return "redirect:/login?error";
        }
    }


    // Método privado para no repetir código
    private String loginExitoso(HttpSession session, SocioDTO socio, HttpServletResponse response,
                                RedirectAttributes redirectAttributes, String redirectUrl) {
        String jwtToken = jwtUtil.generateToken(socio.getNumero_carnet());
        session.setAttribute("jwtToken", jwtToken);
        session.setAttribute("socioLogeado", socio);
        response.setHeader("Authorization", "Bearer " + jwtToken);
        redirectAttributes.addFlashAttribute("Exito", "Usted sí cumple con los requisitos de ingreso al sistema");
        return "redirect:" + redirectUrl;
    }


    //ULTIMO código

    @PostMapping("/guardarsocio")
    public String guardarsocio(@RequestParam("nombresocio") String nombresocio,
                               @RequestParam("apellidopaternosocio") String apellidopaternosocio,
                               @RequestParam("apellidomaternosocio") String apellidomaternosocio,
                               @RequestParam("direccion") String direccion,
                               @RequestParam("distrito") String distrito,
                               @RequestParam("estadocivil") String estadocivil,
                               @RequestParam("dni") String dni,
                               @RequestParam("numero_carnet") String numero_carnet,
                               @RequestParam("telefono") String telefono,
                               @RequestParam("contrasena")String contrasena,
                               @RequestParam("tipo") String tipo,
                               @RequestParam("correo") String correo, Model model) {

        SocioDTO socio1 = new SocioDTO();
        socio1.setNombresocio(nombresocio);
        socio1.setApellidopaternosocio(apellidopaternosocio);
        socio1.setApellidomaternosocio(apellidomaternosocio);
        socio1.setDireccion(direccion);
        socio1.setDistrito(distrito);
        socio1.setEstadocivil(estadocivil);
        socio1.setDni(dni);
        socio1.setNumero_carnet(numero_carnet);
        socio1.setTelefono(telefono);
        socio1.setContrasena(contrasena);
        socio1.setEstado("Activo");
        socio1.setTipo(tipo);
        socio1.setCorreo(correo);

        try{

            socioService.guardar(socio1);

            //Enviar correo con el enlace de verificación
            model.addAttribute("mensajeExito", "Socio registrado correctamente");
            return "redirect:/socios/lista";
        }catch(IllegalArgumentException e)
        {
            model.addAttribute("mensajeError", e.getMessage());
            return "RegistroSocio";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("mensajeError", "Ocurrió un error inesperado. Intente nuevamente.");
            return "RegistroSocio";
        }



    }

    @GetMapping("/confirmarcorreo")
    public String confirmarcorreo(@RequestParam("token") String token,Model model)
    {
        boolean confirmado=socioService.confirmarCorreo(token);

        if(confirmado)
        {
            model.addAttribute("mensajeExito","¡Correo confirmado correctamente! Ya puedes iniciar sesión");
            return "ConfirmacionExitosa";

        }
        else{
            model.addAttribute("mensajeError", "El enlace de confirmación no es válido o ha expirado.");
            return "ConfirmacionFallida";
        }
    }



}
