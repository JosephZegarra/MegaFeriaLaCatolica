package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagosRepository extends JpaRepository<PagosEntity, Integer> {

    @Query("""
    SELECT p 
    FROM PagosEntity p
    JOIN p.deuda d
    JOIN d.socioAsistenciaEntity sa
    JOIN sa.socio s
    WHERE s.id = :idsocio
""")
    List<PagosEntity> findPagosByIdSocio(@Param("idsocio") Integer idsocio);

}
