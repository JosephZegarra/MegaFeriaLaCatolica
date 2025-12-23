package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioAsistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SocioAsistenciaRepository extends JpaRepository<SocioAsistenciaEntity,Integer> {
  // boolean existsByAsistencia_Idasistencia(Integer idAsistencia);
   boolean existsBySocio_IdsocioAndAsistencia_Idasistencia(Integer idSocio, Integer idAsistencia);

    @Query("SELECT sa.asistencia.fecha AS fecha, COUNT(sa) AS cantidad " +
            "FROM SocioAsistenciaEntity sa " +
            "WHERE sa.estado = 'PRESENTE' " +
            "AND sa.asistencia.fecha BETWEEN :from AND :to " +
            "GROUP BY sa.asistencia.fecha " +
            "ORDER BY sa.asistencia.fecha")
    List<Object[]> tendenciaEntre(@Param("from") LocalDateTime from,
                                  @Param("to") LocalDateTime to);

    @Query("SELECT sa.estado, COUNT(sa) " +
            "FROM SocioAsistenciaEntity sa " +
            "GROUP BY sa.estado")
    List<Object[]> contarPorEstado();

    // Top deudores
    @Query("""
        SELECT s.nombresocio, SUM(d.monto)
        FROM DeudaEntity d
        JOIN d.socioAsistenciaEntity sa
        JOIN sa.socio s
        GROUP BY s.nombresocio
        ORDER BY SUM(d.monto) DESC
        """)
    List<Object[]> topDeudores();




 /*
    @Query("""
    SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
        CAST(a.fecha AS date),
        COUNT(sa.socio.idsocio),
        SUM(CASE WHEN sa.estado = 'Ausente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' AND sa.motivo IS NOT NULL THEN 1 ELSE 0 END)
    )
    FROM SocioAsistenciaEntity sa
    JOIN sa.asistencia a
    WHERE a.fecha BETWEEN :from AND :to
    GROUP BY CAST(a.fecha AS date)
    ORDER BY CAST(a.fecha AS date)
    """)
    List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
*/
 /*
    @Query("""
    SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
        DATE(a.fecha),  
        COUNT(sa.socio),
        SUM(CASE WHEN sa.estado = 'Ausente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' AND sa.motivo IS NOT NULL THEN 1 ELSE 0 END)
    )
    FROM SocioAsistenciaEntity sa
    JOIN sa.asistencia a
    WHERE a.fecha BETWEEN :from AND :to
    GROUP BY DATE(a.fecha)
    ORDER BY DATE(a.fecha)
""")
    List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

*/
 /*
//TITULAR
    @Query("SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(" +
            "CAST(a.fecha AS date), " +
            "SUM(CASE WHEN sa.estado = 'Presente' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN sa.estado = 'Ausente' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN sa.estado = 'Ausente' AND sa.motivo IS NOT NULL THEN 1 ELSE 0 END)) " +
            "FROM SocioAsistenciaEntity sa " +
            "JOIN sa.asistencia a " +
            "WHERE CAST(a.fecha AS date) BETWEEN :from AND :to " +
            "GROUP BY CAST(a.fecha AS date) " +
            "ORDER BY CAST(a.fecha AS date)")
    List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
*/
/*
 @Query("""
    SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
        FUNCTION('DATE', a.fecha),
        SUM(CASE WHEN sa.estado = 'Presente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' AND sa.motivo IS NOT NULL THEN 1 ELSE 0 END)
    )
    FROM SocioAsistenciaEntity sa
    JOIN sa.asistencia a ON sa.idsocio = sa.idsocio
    WHERE FUNCTION('DATE', a.fecha) BETWEEN :from AND :to
    GROUP BY FUNCTION('DATE', a.fecha)
    ORDER BY FUNCTION('DATE', a.fecha)
""")
 List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
         @Param("from") LocalDate from,
         @Param("to") LocalDate to
 );

*/
 /*
    @Query("""
    SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
        FUNCTION('DATE', a.fecha),
        SUM(CASE WHEN sa.estado = 'Presente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' THEN 1 ELSE 0 END),
        SUM(CASE WHEN sa.estado = 'Ausente' AND sa.motivo IS NOT NULL THEN 1 ELSE 0 END)
    )
    FROM SocioAsistenciaEntity sa
    JOIN AsistenciaEntity a ON a.idasistencia = sa.asistencia.idasistencia
    WHERE FUNCTION('DATE', a.fecha) BETWEEN :from AND :to
    GROUP BY FUNCTION('DATE', a.fecha)
    ORDER BY FUNCTION('DATE', a.fecha)
""")
    List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
*/
/*
 @Query("""
SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
    CAST(a.fecha AS date),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Presente' THEN sa.idSocioAsistencia END),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Ausente'  THEN sa.idSocioAsistencia END),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Ausente' AND sa.motivo <> '' THEN sa.idSocioAsistencia END)
)
FROM SocioAsistenciaEntity sa
JOIN sa.asistencia a
WHERE CAST(a.fecha AS date) BETWEEN :from AND :to
GROUP BY CAST(a.fecha AS date)
ORDER BY CAST(a.fecha AS date)
""")
 List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
         @Param("from") LocalDate from,
         @Param("to") LocalDate to
 );
*/
 @Query("""
SELECT new com.alexander.curso.spring.boot.webapp.springboot_web.dto.ResumenAsistenciaDTO(
    CAST(a.fecha AS date),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Presente' THEN sa.socio.id END),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Ausente'  THEN sa.socio.id END),
    COUNT(DISTINCT CASE WHEN sa.estado = 'Ausente' AND sa.motivo <> '' THEN sa.socio.id END)
)
FROM SocioAsistenciaEntity sa
JOIN sa.asistencia a
WHERE CAST(a.fecha AS date) BETWEEN :from AND :to
GROUP BY CAST(a.fecha AS date)
ORDER BY CAST(a.fecha AS date)
""")
 List<ResumenAsistenciaDTO> obtenerResumenPorFecha(
         @Param("from") LocalDate from,
         @Param("to") LocalDate to
 );





}
