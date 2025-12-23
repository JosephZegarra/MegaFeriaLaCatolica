package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long>
{
   List<MovimientoEntity> findByTipo(String tipo);
/*
   @Query("""
SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoDTO(
    m.fecha,
    SUM(CASE WHEN m.tipo = 'INGRESO' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN m.tipo = 'EGRESO' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN m.tipo = 'INGRESO' THEN m.monto ELSE 0 END) -
    SUM(CASE WHEN m.tipo = 'EGRESO' THEN m.monto ELSE 0 END)
)
FROM MovimientoEntity m
WHERE m.fecha BETWEEN :from AND :to
GROUP BY m.fecha
ORDER BY m.fecha
""")
   List<MovimientoDTO> obtenerMovimientosPorFecha(
           @Param("from") LocalDate from,
           @Param("to") LocalDate to
   );
*/

@Query("""
SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO(
    m.fecha,
    SUM(CASE WHEN m.tipo = 'Ingreso' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN m.tipo = 'Egreso' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN m.tipo = 'Ingreso' THEN m.monto ELSE 0 END) -
    SUM(CASE WHEN m.tipo = 'Egreso' THEN m.monto ELSE 0 END)
)
FROM MovimientoEntity m
WHERE m.fecha BETWEEN :from AND :to
GROUP BY m.fecha
ORDER BY m.fecha
""")
List<MovimientoResumenDTO> obtenerMovimientosPorFecha(
        @Param("from") LocalDate from,
        @Param("to") LocalDate to
);


   @Query("SELECT MAX(m.fecha) FROM MovimientoEntity m")
   LocalDate obtenerFechaMaxima();
/*
   @Query("""
SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.MovimientoResumenDTO(
    FUNCTION('YEAR', m.fecha),  
    FUNCTION('MONTH', m.fecha), 
    SUM(CASE WHEN UPPER(m.tipo) = 'Ingreso' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN UPPER(m.tipo) = 'Egreso' THEN m.monto ELSE 0 END),
    SUM(CASE WHEN UPPER(m.tipo) = 'Ingreso' THEN m.monto ELSE 0 END) -
    SUM(CASE WHEN UPPER(m.tipo) = 'Egreso' THEN m.monto ELSE 0 END)
)
FROM MovimientoEntity m
WHERE m.fecha BETWEEN :from AND :to
GROUP BY FUNCTION('YEAR', m.fecha), FUNCTION('MONTH', m.fecha)
ORDER BY FUNCTION('YEAR', m.fecha), FUNCTION('MONTH', m.fecha)
""")
   List<MovimientoResumenDTO> obtenerMovimientosPorMes(
           @Param("from") LocalDate from,
           @Param("to") LocalDate to
   );
*/
}
