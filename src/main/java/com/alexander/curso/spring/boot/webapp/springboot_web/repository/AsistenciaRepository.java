package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.AsistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<AsistenciaEntity,Integer> {
    List<AsistenciaEntity> findByTipoeventoNot(String tipoevento);

    /*
    boolean existsByTipoeventoAndFechaAndDescripcion(String tipoevento, LocalDateTime fecha, String descripcion);

    boolean existsByTipoeventoAndDescripcionAndFechaBetween(
            String tipoevento,
            String descripcion,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );*/
    boolean existsByIdasistenciaAndSocios_Idsocio(Integer idasistencia, Integer idsocio);

    /*
    boolean existsBySocioAndTipoeventoAndFechaAndDescripcion(
            String usuario, String tipoevento, LocalDateTime fecha, String descripcion
    );
    */

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM AsistenciaEntity a JOIN a.socios s " +
            "WHERE s.id = :socioId " +
            "AND a.tipoevento = :tipoevento " +
            "AND a.fecha = :fecha " )
    boolean existsBySocioAndTipoeventoAndFechaAndDescripcion(
            @Param("socioId") Integer socioId,
            @Param("tipoevento") String tipoevento,
            @Param("fecha") LocalDate fecha

    );
    Optional<AsistenciaEntity> findByTipoeventoAndFechaAndDescripcion(String tipoevento, LocalDateTime fecha, String descripcion);


}
