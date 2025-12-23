package com.alexander.curso.spring.boot.webapp.springboot_web.controller;


import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.PagosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PagosJsonController
{
    @Autowired
    private PagosService pagosService;

    @PostMapping("/registrarPago")
    public ResponseEntity<?> registrarPagoApi(HttpSession session,
            @RequestParam Integer iddeuda, @RequestParam BigDecimal monto
    ) {
        try {
            SocioDTO socioDTO=(SocioDTO) session.getAttribute("socioLogeado");

            if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body(
                        Map.of(
                                "status", "error",
                                "mensaje", "El monto debe ser mayor a 0"
                        )
                );
            }

            pagosService.registrarpago(iddeuda, monto,socioDTO.getNumero_carnet());

            return ResponseEntity.ok(
                    Map.of(
                            "status", "success",
                            "mensaje", "Pago registrado correctamente",
                            "iddeuda", iddeuda,
                            "monto", monto
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", "error",
                            "mensaje", e.getMessage()
                    )
            );
        }

    }

}
