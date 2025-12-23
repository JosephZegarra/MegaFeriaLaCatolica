package com.alexander.curso.spring.boot.webapp.springboot_web.controller;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DeudaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deudas")

public class DeudaJsonController
{
    @Autowired
    private SocioService socioService;

    @Autowired
    private DeudaService deudaService;


    @GetMapping("/deudasocio")
    public ResponseEntity<?> obtenerdeudasocio(@RequestParam("numero_carnet") String numero_carnet) {

        SocioDTO socioDTO = socioService.BuscarPorNumero_Carnet(numero_carnet);

        if (socioDTO == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No se encontró ningún socio con ese número de carnet"));
        }

        List<DeudaEntity> deudasPendientes = deudaService.obtenerDeudaPendientePorSocio(socioDTO.getIdsocio());

        if (deudasPendientes == null || deudasPendientes.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "El socio no tiene deudas registradas",
                    "socio", socioDTO
            ));
        }

        List<Map<String, Object>> deudasConSaldo = new ArrayList<>();

        for (DeudaEntity deuda : deudasPendientes) {

            BigDecimal totalPagado = deuda.getPagos().stream()
                    .map(PagosEntity::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal saldoPendiente = deuda.getMonto().subtract(totalPagado);

            if (saldoPendiente.compareTo(BigDecimal.ZERO) < 0) {
                saldoPendiente = BigDecimal.ZERO;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("iddeuda", deuda.getIddeuda());
            data.put("estado", deuda.getEstado());
            data.put("monto_original", deuda.getMonto());
            data.put("saldo_pendiente", saldoPendiente);
            deudasConSaldo.add(data);
        }

        BigDecimal totalDeuda = deudaService.obtenerdeudaporsocio(socioDTO.getIdsocio());

        return ResponseEntity.ok(
                Map.of(
                        "socio", socioDTO,
                        "deudas", deudasConSaldo,
                        "totalDeuda", totalDeuda
                )
        );
    }
}
