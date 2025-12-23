package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.*;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.DeudaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.TipoDeudaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.DeudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeudaServiceImpl implements DeudaService
{
    @Autowired
    private SocioMapper socioMapper;

    @Autowired
    private TipoDeudaRepository tipoDeudaRepository;

    @Autowired
    private DeudaRepository deudaRepository;

    @Override
    public void generardeudaporasistencia(List<SocioAsistenciaEntity> listsocioasistencia)
    {
        TipoDeudaEntity tipoDeudaEntity=tipoDeudaRepository.findByNombre("FaltarAsociacion").orElseThrow(() -> new RuntimeException("Tipo de deuda 'Inasistencia' no configurado"));

        for(SocioAsistenciaEntity socioAsistencia:listsocioasistencia)
        {
            if("Ausente".equalsIgnoreCase(socioAsistencia.getEstado()))
            {
                DeudaEntity deuda=new DeudaEntity();
                deuda.setSocioAsistenciaEntity(socioAsistencia);
                deuda.setTipoDeuda(tipoDeudaEntity);
                deuda.setMonto(tipoDeudaEntity.getMonto());
                deuda.setEstado("Pendiente");
                //guardando
                deudaRepository.save(deuda);

            }
        }
    }

    @Override
    public BigDecimal obtenerdeudaporsocio(Integer idsocio) {
        //lista de deudas por socio
        List<DeudaEntity> deudas = deudaRepository.findBySocioIdsocio(idsocio);

        //si la deuda esta vacia , es decir no debe nada
        if(deudas.isEmpty())
        {
            System.out.println("El socio no tinene deuda registrada");
            return BigDecimal.ZERO;
        }
        BigDecimal totalPendiente=BigDecimal.ZERO;

        //Capturo lo que ha pagado y lo que debe  y lo devuelvo
        for(DeudaEntity deuda: deudas)
        {
            //calculo el total que pago el socio
            BigDecimal totalPagado=deuda.getPagos().stream().map(PagosEntity::getMonto).reduce(BigDecimal.ZERO, BigDecimal::add);

            //calcular cuanto debe : totalpagado-pendiente
            BigDecimal pendiente= deuda.getMonto().subtract(totalPagado);//en el primera vez que corre la lista resta si pago como
                                                                          // no pago los 50 soles se va acumuladon en total pendiente y se va sumando la
                                                                           //lista

            if(pendiente.compareTo(BigDecimal.ZERO)>0)
            {
                totalPendiente=totalPendiente.add(pendiente);//aqui caluclo lo que debe sumo lo que debe +50 soles.
            }
        }

        if(totalPendiente.compareTo(BigDecimal.ZERO)==0)
        {
            System.out.println("El socio no tiene deudas pendientes");
        }
        return totalPendiente;
    }


    @Override
    public List<DeudaEntity> obtenerDeudaPendientePorSocio(Integer idsocio)
    {
        List<DeudaEntity>deudas=deudaRepository.findBySocioIdsocio(idsocio);

        return deudas.stream().filter( d->"Pendiente".equalsIgnoreCase(d.getEstado()) ).collect(Collectors.toList());
    }

    @Override
    public DeudaEntity obtenerDeudaPorId(Integer iddeuda)
    {
        return deudaRepository.findById(iddeuda).orElse(null);
    }

    @Override
    public SocioDTO obtenerSocioPorIdDeuda(Integer iddeuda)
    {
        DeudaEntity deuda=deudaRepository.findById(iddeuda).orElseThrow(()-> new RuntimeException("Deuda no encontrada"));
        SocioEntity socioEntity= deuda.getSocioAsistenciaEntity().getSocio();
        return socioMapper.SocioEntityASocioDTO(socioEntity);
    }
}



