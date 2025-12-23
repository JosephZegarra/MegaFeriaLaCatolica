package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.AsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaService
{
    public AsistenciaDTO guardarasistenciatabla(AsistenciaDTO asistenciaDTO);
    /*public void guardarasistenciasocios(Integer idAsistencia, List<Integer> idsSocios,
                                  List<String> estados,
                                  List<String> puntualidades,
                                  List<String> motivos
    );*/


    public List<SocioAsistenciaEntity> guardarasistenciasocios(Integer idAsistencia,
                                                               List<Integer> idsSocios,
                                                               List<String> estados,
                                                               List<String> puntualidades,
                                                               List<String> motivos);


    public boolean existeAsistencia(Integer idasistencia, Integer idsocio);
    public boolean existeAsistenciaParaDelegado(String numero_carnet, Integer idAsistencia);
    public AsistenciaDTO obtenerAsistenciaPorReunion(String tipoevento, LocalDateTime fecha, String descripcion);
    AsistenciaDTO obtenerPorId(Integer idAsistencia);
    public List<AsistenciaDTO> listarTodas();

    List<ResumenAsistenciaDTO> obtenerResumenPorFecha(LocalDate from, LocalDate to);


}
