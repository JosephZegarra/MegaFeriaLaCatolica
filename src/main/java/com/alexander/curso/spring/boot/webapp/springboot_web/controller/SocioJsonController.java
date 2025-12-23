package com.alexander.curso.spring.boot.webapp.springboot_web.controller;


import com.alexander.curso.spring.boot.webapp.springboot_web.dto.LoginRequestDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/socios")//Mapea las rutas
//Se encarga de escuchar y responder las peticiones del cliente
public class SocioJsonController {
    @Autowired
    private SocioService socioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SocioRolRepository socioRolRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private SocioMapper socioMapper;
    //METODOS DEL CRUD

    @GetMapping("/listar")
    public ResponseEntity<List<SocioDTO>> listar() {
        return new ResponseEntity<>(socioService.listar(), HttpStatus.OK);
    }
    @PostMapping("/editarsocio")
    public ResponseEntity<?> editarSocio(@RequestBody SocioDTO socio) {
        socioService.editar(socio);
        return ResponseEntity.ok().body(
                Map.of("message", "Socio actualizado correctamente")
        );
    }

    @PostMapping("/ver")
    public ResponseEntity<?> verSocio(@RequestParam Integer idsocio) {
        SocioDTO socio = socioService.BuscarPorId(idsocio);

        if (socio == null) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "message", "Socio no encontrado",
                            "idBuscado", idsocio
                    )
            );
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Socio obtenido correctamente",
                        "socio", socio
                )
        );
    }

    @PostMapping("/borrar")
    public ResponseEntity<?> borrarSocio(@RequestParam Integer idsocio) {
        socioService.borrar(idsocio);
        return ResponseEntity.ok().body(
                Map.of("message", "Socio eliminado correctamente")
        );
    }

    //Confirmar correo electronico
    @GetMapping("/confirmarcorreo")
    public ResponseEntity<Map<String, Object>> confirmarCorreo(@RequestParam("token") String token) {

        boolean confirmado = socioService.confirmarCorreo(token);

        Map<String, Object> response = new HashMap<>();

        if (confirmado) {
            response.put("message", "¡Correo confirmado correctamente! Ya puedes iniciar sesión.");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "El enlace de confirmación no es válido o ha expirado.");
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }


    //Guardar Socio
    @PostMapping("/guardarsocio")
    public ResponseEntity<?> guardarSocio(@RequestBody SocioDTO socio) {

        try {
            socioService.guardar(socio);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Socio registrado correctamente");
            response.put("socio", socio);

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {

            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            Map<String, Object> response = new HashMap<>();
            response.put("error", "Ocurrió un error inesperado. Intente nuevamente.");
            return ResponseEntity.status(500).body(response);
        }
    }





    @PostMapping("/buscarsocio")
    public ResponseEntity<?> buscarsocio(
            @RequestBody LoginRequestDTO request,
            HttpSession session,
            HttpServletResponse response) {

        String numero_carnet = request.getNumero_carnet();
        String contrasena = request.getContrasena();
        String rol = request.getRol();

        Map<String, Object> json = new HashMap<>();

        // 1) Buscar socio por carnet
        SocioEntity socioEntity = socioRepository.findByNumeroCarnet(numero_carnet).orElse(null);

        if (socioEntity == null) {
            json.put("error", "Número de carnet o contraseña incorrecto");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        // 2) Validar bloqueo temporal
        if (Boolean.TRUE.equals(socioEntity.getCuenta_bloqueada())) {
            LocalDateTime fechaBloqueo = socioEntity.getFecha_bloqueo();
            if (fechaBloqueo != null) {
                long minutos = Duration.between(fechaBloqueo, LocalDateTime.now()).toMinutes();//calucla cuanto a pasado desde la fecha de bloqueo hasta ahora.
                if (minutos < 5) {
                    json.put("error", "Cuenta bloqueada. Intente en " + (5 - minutos) + " minutos.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);//eror 401
                }
                // desbloqueo automático
                socioEntity.setCuenta_bloqueada(false);
                socioEntity.setIntentos_fallidos(0);
                socioEntity.setFecha_bloqueo(null);
                socioRepository.save(socioEntity);
            } else {
                json.put("error", "Su cuenta está bloqueada por demasiados intentos fallidos.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            }
        }

        // 3) Validar contraseña
        SocioDTO socioDTO = socioService.validarSocio(numero_carnet, contrasena);

        if (socioDTO == null) {
            int intentos = socioEntity.getIntentos_fallidos() == null ? 0 : socioEntity.getIntentos_fallidos();
            intentos++;
            socioEntity.setIntentos_fallidos(intentos);

            if (intentos >= 5) {
                socioEntity.setCuenta_bloqueada(true);
                socioEntity.setFecha_bloqueo(LocalDateTime.now());
                socioRepository.save(socioEntity);

                json.put("error", "Cuenta bloqueada por demasiados intentos fallidos.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            }

            socioRepository.save(socioEntity);

            json.put("error", "Número de carnet o contraseña incorrecto");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        // 4) Resetear intentos
        socioEntity.setIntentos_fallidos(0);
        socioEntity.setCuenta_bloqueada(false);
        socioEntity.setFecha_bloqueo(null);
        socioRepository.save(socioEntity);

        // 5) Convertir socio
        SocioDTO socio = socioMapper.SocioEntityASocioDTO(socioEntity);

        // 6) Estado inactivo
        if ("Inactivo".equalsIgnoreCase(socio.getEstado())) {
            json.put("error", "El socio ya no pertenece a la asociación");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        // 7) Rol activo
        String rolActivo = socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);

        if ("Activo".equalsIgnoreCase(socio.getEstado()) && rolActivo == null) {
            if (!"Socio".equalsIgnoreCase(rol)) {
                json.put("error", "No tiene cargo asignado, solo puede ingresar como Socio");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            }
            return loginExitosoJson(session, socio, response, "/paginas/menusocio");
        }

        if (!rolActivo.equalsIgnoreCase(rol)) {
            json.put("error", "El rol seleccionado no coincide con el registrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }

        // 8) Redirigir por rol
        switch (rolActivo) {
            case "Presidente":
                return loginExitosoJson(session, socio, response, "/paginas/menupresidente");
            case "Secretaria":
                return loginExitosoJson(session, socio, response, "/paginas/menusecretaria");
            case "Tesorera":
                return loginExitosoJson(session, socio, response, "/paginas/tesorera");
            case "Delegadoporrubro":
                return loginExitosoJson(session, socio, response, "/paginas/delegadoporrubro");
            default:
                json.put("error", "Rol no válido");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }
    }

    private ResponseEntity<?> loginExitosoJson(
            HttpSession session,
            SocioDTO socio,
            HttpServletResponse response,
            String redirectUrl) {

        Map<String, Object> json = new HashMap<>();

        String jwtToken = jwtUtil.generateToken(socio.getNumero_carnet());

        session.setAttribute("jwtToken", jwtToken);
        session.setAttribute("socioLogeado", socio);

        response.setHeader("Authorization", "Bearer " + jwtToken);

        json.put("mensaje", "Login exitoso");
        json.put("token", jwtToken);
        json.put("redirect", redirectUrl);
        json.put("socio", socio);

        return ResponseEntity.ok(json);
    }

}
