package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.AsistenciaMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.AsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioAsistenciaRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaServiceImpl implements AsistenciaService
{
    @Autowired
    AsistenciaRepository asistenciaRepository;
    @Autowired
    AsistenciaMapper asistenciaMapper;

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    SocioAsistenciaRepository socioAsistenciaRepository;

    @Override
    public AsistenciaDTO guardarasistenciatabla(AsistenciaDTO asistenciaDTO)
    {
        AsistenciaEntity asistenciaEntity=asistenciaMapper.AsistenciaDTOAAsistenciaEntity(asistenciaDTO);
        AsistenciaEntity savedEntity=asistenciaRepository.save(asistenciaEntity);

        return asistenciaMapper.AsistenciaEntityAAsistenciaDTO(savedEntity);
    }

    @Override
    public List<SocioAsistenciaEntity> guardarasistenciasocios( Integer idAsistencia, List<Integer> idsSocios,
                                         List<String> estados,
                                         List<String> puntualidades,
                                         List<String> motivos)
    {
        //creo arreglo (Nuevo)
        List<SocioAsistenciaEntity>lista=new ArrayList<>();

        AsistenciaEntity asistencia= asistenciaRepository.findById(idAsistencia) .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + idAsistencia));;

        for (int i = 0; i < idsSocios.size(); i++)
        {

            SocioAsistenciaEntity registro=new SocioAsistenciaEntity();
            Integer idSocio=idsSocios.get(i);

            SocioEntity socio=socioRepository.findById(idSocio).orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + idSocio));

            registro.setAsistencia(asistencia);
            registro.setSocio(socio);
            registro.setEstado(estados.get(i));
            registro.setPuntualidad(puntualidades.get(i));
            registro.setMotivo(motivos.get(i));


            socioAsistenciaRepository.save(registro);
            lista.add(registro);

        }
        return lista;
    }

    /*
    @Override
    public boolean existeAsistencia(String tipoevento, LocalDateTime fecha, String descripcion)
    {    LocalDate dia = fecha.toLocalDate();
        return asistenciaRepository.existsByTipoeventoAndDescripcionAndFechaBetween(
                tipoevento,
                descripcion,
                dia.atStartOfDay(),
                dia.atTime(23, 59, 59)
        );

        //return asistenciaRepository.existsByTipoeventoAndFechaAndDescripcion(tipoevento,fecha,descripcion);
    }

     */

    @Override
    public boolean existeAsistencia(Integer idasistencia, Integer idsocio)
    {
        return asistenciaRepository.existsByIdasistenciaAndSocios_Idsocio(idasistencia, idsocio);
    }


    @Override
    public boolean existeAsistenciaParaDelegado(String numero_carnet, Integer idAsistencia) {

        Optional<SocioEntity> socioOpt = socioRepository.findByNumeroCarnet(numero_carnet);
        if (socioOpt.isEmpty()) return false;

        SocioEntity socio = socioOpt.get();

        return socioAsistenciaRepository.existsBySocio_IdsocioAndAsistencia_Idasistencia(
                socio.getIdsocio(),
                idAsistencia
        );
    }


    @Override
    public AsistenciaDTO obtenerAsistenciaPorReunion(String tipoevento, LocalDateTime fecha, String descripcion) {
        return asistenciaRepository
                .findByTipoeventoAndFechaAndDescripcion(tipoevento, fecha, descripcion)
                .map(asistenciaMapper::AsistenciaEntityAAsistenciaDTO)
                .orElse(null);
    }

    @Override
    public AsistenciaDTO obtenerPorId(Integer idasistencia)
    {
        AsistenciaEntity asistenciaEntity=asistenciaRepository.findById(idasistencia).orElse(null);

        if(asistenciaEntity==null){
            return null;
        }
        return asistenciaMapper.AsistenciaEntityAAsistenciaDTO(asistenciaEntity);
    }

    @Override
    public List<AsistenciaDTO> listarTodas() {
        List<AsistenciaEntity> entidades = asistenciaRepository.findAll();
        // Convertir a DTO si usas DTO
        return entidades.stream()
                .map(e -> {
                    AsistenciaDTO dto = new AsistenciaDTO();
                    dto.setIdasistencia(e.getIdasistencia());
                    dto.setTipoevento(e.getTipoevento());
                    dto.setDescripcion(e.getDescripcion());
                    dto.setFecha(e.getFecha());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<ResumenAsistenciaDTO> obtenerResumenPorFecha(LocalDate from, LocalDate to) {
        return socioAsistenciaRepository.obtenerResumenPorFecha(from, to);
    }





}
