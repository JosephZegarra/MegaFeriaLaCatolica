package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/paginas")
public class PaginasJsonController
{

    @Autowired
    private JwtUtil jwtUtil;

    /*
    @GetMapping("/menusecretaria")
    public ResponseEntity<?> menusecretaria(HttpSession session)
    {
        String token = (String) session.getAttribute("jwtToken");

        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "Sesión expirada o no válida. Inicie sesión nuevamente."));
        }

        SocioDTO socio = (SocioDTO) session.getAttribute("socioLogeado");

        return ResponseEntity.ok(Map.of(
                "mensaje", "Acceso permitido",
                "socio", socio
        ));
    }

 */
    @GetMapping("/menusecretaria")
    public ResponseEntity<?> menusecretaria(@RequestHeader(value = "Authorization", required = false) String authHeader)
    {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Token no enviado."));
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido o expirado."));
        }

       // SocioDTO socio = jwtUtil.obtenerSocioDesdeToken(token);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Acceso permitido")
        );
    }

}
