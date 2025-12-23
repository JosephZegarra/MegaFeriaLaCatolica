package com.alexander.curso.spring.boot.webapp.springboot_web.controller;


import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoRequestDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.MovimientoService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoJsonController
{
    @Autowired
    private SocioService socioService;

    @Autowired
    private SocioRolRepository socioRolRepository;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private JwtUtil jwtUtil;
    /*
    @PostMapping("/guardarmovimiento")
    public ResponseEntity<?> guardarmovimiento(
            @RequestParam String tipo,
            @RequestParam String descripcion,
            @RequestParam BigDecimal monto,
            @RequestParam String numero_carnet
    ) {

        // 1. Validar monto negativo
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "El monto debe ser mayor que 0")
            );
        }

        // 2. Buscar socio
        SocioDTO socioDTO = socioService.BuscarPorNumero_Carnet(numero_carnet);
        if (socioDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "El número de carnet ingresado no existe")
            );
        }

        // 3. Validar estado
        if (socioDTO.getEstado().equalsIgnoreCase("Inactivo")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "El socio ya no pertenece a la asociación")
            );
        }

        // 4. Validar rol activo
        String rolactivo = socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);
        if (!rolactivo.equalsIgnoreCase("Tesorera")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "El socio debe tener el rol de Tesorera para registrar movimientos")
            );
        }

        // 5. Guardar movimiento
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setIdsocio(socioDTO.getIdsocio());
        movimientoDTO.setTipo(tipo);
        movimientoDTO.setDescripcion(descripcion);
        movimientoDTO.setMonto(monto);
        movimientoDTO.setFecha(LocalDate.now());

        movimientoService.guardar(movimientoDTO);

        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "mensaje", "Movimiento registrado correctamente",
                        "tipo", tipo,
                        "monto", monto,
                        "idsocio", socioDTO.getIdsocio()
                )
        );
    }
   */
    /*
    @PostMapping("/guardarmovimiento")
    public ResponseEntity<?> guardarmovimiento(
            @RequestHeader(value = "Authorization", required = false) String authHeader1,
            @RequestBody MovimientoRequestDTO request) {

        // 0. Validar token
        if (authHeader1 == null || !authHeader1.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Token no enviado."));
        }

        // Aquí podrías agregar la validación real del token si quieres
        // Por ejemplo, verificar JWT o cualquier otro mecanismo
        // if (!validarToken(token)) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        //         Map.of("error", "Token inválido")
        //     );
        // }

        String tipo = request.getTipo();
        String descripcion = request.getDescripcion();
        BigDecimal monto = request.getMonto();
        String numero_carnet = request.getNumero_carnet();

        // 1. Validar monto
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "El monto debe ser mayor a 0")
            );
        }

        // 2. Buscar socio
        SocioDTO socioDTO = socioService.BuscarPorNumero_Carnet(numero_carnet);
        if (socioDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "El número de carnet no existe")
            );
        }

        // 3. Validar estado del socio
        if (socioDTO.getEstado().equalsIgnoreCase("Inactivo")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "El socio ya no pertenece a la asociación")
            );
        }

        // 4. Validar rol activo
        String rolactivo = socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);
        if (!rolactivo.equalsIgnoreCase("Tesorera")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "Debe tener el rol de Tesorera para registrar movimientos")
            );
        }

        // 5. Crear el movimiento
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setIdsocio(socioDTO.getIdsocio());
        movimientoDTO.setTipo(tipo);
        movimientoDTO.setDescripcion(descripcion);
        movimientoDTO.setMonto(monto);
        movimientoDTO.setFecha(LocalDate.now());

        // 6. Guardar en BD
        movimientoService.guardar(movimientoDTO);

        // 7. Respuesta JSON
        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "mensaje", "Movimiento registrado correctamente",
                        "tipo", tipo,
                        "monto", monto,
                        "idsocio", socioDTO.getIdsocio()
                )
        );
    }

     */
    @PostMapping("/guardarmovimiento")
    public ResponseEntity<?> guardarmovimiento(
            @RequestHeader(value = "Authorization", required = false) String authHeader1,
            @RequestBody(required = false) MovimientoRequestDTO request) {

        // 0. Validar token
        if (authHeader1 == null || !authHeader1.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(
                    Map.of("error", "Token no enviado.")
            );
        }
        String token = authHeader1.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido o expirado."));
        }

        // 0b. Validar que el body no sea nulo
        if (request == null) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Cuerpo de la solicitud vacío")
            );
        }

        String tipo = request.getTipo();
        String descripcion = request.getDescripcion();
        BigDecimal monto = request.getMonto();
        String numero_carnet = request.getNumero_carnet();

        if (!numero_carnet.matches("^[A-Za-z]+-[0-9]+$")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El número de carnet debe tener el formato LETRAS-NÚMEROS, por ejemplo: CARNET-001"));
        }
        // 1. Validar monto
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "El monto debe ser mayor a 0")
            );
        }

        // 2. Buscar socio
        SocioDTO socioDTO = socioService.BuscarPorNumero_Carnet(numero_carnet);
        if (socioDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "El número de carnet no existe")
            );
        }

        // 3. Validar estado del socio
        if (socioDTO.getEstado().equalsIgnoreCase("Inactivo")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "El socio ya no pertenece a la asociación")
            );
        }

        // 4. Validar rol activo
        String rolactivo = socioRolRepository.EncuentraRolActivoPorSocioId(numero_carnet);
        if (!rolactivo.equalsIgnoreCase("Tesorera")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", "Debe tener el rol de Tesorera para registrar movimientos")
            );
        }

        // 5. Crear el movimiento
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setIdsocio(socioDTO.getIdsocio());
        movimientoDTO.setTipo(tipo);
        movimientoDTO.setDescripcion(descripcion);
        movimientoDTO.setMonto(monto);
        movimientoDTO.setFecha(LocalDate.now());

        // 6. Guardar en BD
        movimientoService.guardar(movimientoDTO);

        // 7. Respuesta JSON
        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "mensaje", "Movimiento registrado correctamente",
                        "tipo", tipo,
                        "monto", monto,
                        "idsocio", socioDTO.getIdsocio()
                )
        );
    }


}
