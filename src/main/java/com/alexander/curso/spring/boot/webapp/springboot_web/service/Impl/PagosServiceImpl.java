package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.PagosDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.PagosMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.DeudaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.PagosRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PagosServiceImpl implements PagosService
{

    @Autowired
    private DeudaRepository deudaRepository;

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private PagosMapper pagosMapper;

    @Autowired
    private SocioRepository socioRepository;



    @Override
    public PagosDTO registrarpago(Integer iddeuda, BigDecimal monto, String numero_carnet)
    {

        if (numero_carnet == null) {
            throw new RuntimeException("No se puede registrar el pago: usuario no logeado");
        }

        DeudaEntity deudaEntity=deudaRepository.findById(iddeuda).orElseThrow(() -> new RuntimeException("La deuda con id"+" "+iddeuda+"no existe"));
        //obtengo el carnet del socio
        String carnetsociodedeuda=deudaEntity.getSocioAsistenciaEntity().getSocio().getNumero_carnet();
        if(carnetsociodedeuda.equalsIgnoreCase(numero_carnet))
        {
            throw new RuntimeException("No se puede registrar un pago sobre la propia deuda del registrador.");
        }


        //sumar todos los pagos de esa deuda
        BigDecimal totalPagos = deudaEntity.getPagos().stream()
                .map(PagosEntity::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Calcular el monto que no exceda el monto pendiente
        BigDecimal saldoPendiente=deudaEntity.getMonto().subtract(totalPagos);

        //Calcular el montoque que no exceda el saldo pendiente
        if(monto.compareTo(saldoPendiente)>0)
        {
            throw new RuntimeException("El monto ingresado (" + monto + ") excede el saldo pendiente (" + saldoPendiente + ").");
        }

        PagosEntity pagosEntity=new PagosEntity();
        pagosEntity.setDeuda(deudaEntity);
        pagosEntity.setMonto(monto);
        pagosEntity.setFecha(LocalDate.now());
        pagosEntity.setCarnetRegistrador(numero_carnet);
        //lo guardo en la base de datos
        pagosRepository.save(pagosEntity);

        //Recalcular total pagado despues del nuevo pago
        totalPagos=totalPagos.add(monto);

        //
        if (totalPagos.compareTo(deudaEntity.getMonto()) >= 0) {
            deudaEntity.setEstado("Pagado");
            deudaRepository.save(deudaEntity);
        }else{
            deudaEntity.setEstado("Pendiente");
            deudaRepository.save(deudaEntity);
        }

        /*
        BigDecimal nuevoSaldo = deudaEntity.getMonto().subtract(totalPagos);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) <= 0) {
            deudaEntity.setMonto(BigDecimal.ZERO);
            deudaEntity.setEstado("Pagado");
        } else {
            deudaEntity.setMonto(nuevoSaldo);
            deudaEntity.setEstado("Pendiente");
        }
        */
        //deudaRepository.save(deudaEntity);

        PagosDTO pagosDTO=pagosMapper.PagosEntityAPagosDTO(pagosEntity);
        return pagosDTO;

    }

    @Override
    public List<PagosDTO> obtenerPagosPorSocio(Integer idsocio)
    {
        //SocioDTO socioDTO=(SocioDTO) session.getAttribute("socioLogeado");
        List<PagosEntity>pagos= pagosRepository.findPagosByIdSocio(idsocio);
        List<PagosDTO>pagosDTO=pagos.stream().map(pago->{
            PagosDTO dto=pagosMapper.PagosEntityAPagosDTO(pago);

            if(pago.getCarnetRegistrador()!=null)
            {
                SocioEntity socioEntity =socioRepository.findByNumeroCarnet(pago.getCarnetRegistrador()).orElseThrow(() -> new RuntimeException("No se encontró el usuario con carnet " + pago.getCarnetRegistrador()));
                dto.setNombreRegistrador(socioEntity.getNombresocio());//Lo que hace stream es separar en identidades
                                                                       // y map para que cada pago dto funcione de forma separada
            }                                                         // y le envio el nommbre

            return dto;//devuelvo la lista de pagos

        }).collect(Collectors.toList());

        return pagosDTO;
        //return  pagosMapper.pagosEntityListToDTO(pagos);
    }

}
