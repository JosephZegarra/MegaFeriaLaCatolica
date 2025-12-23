package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocioRolRepository extends JpaRepository<SocioRolEntity,Long>
{
    //Muestra el nombre del rol que esta registrado SocioRolEntity en base al id del socio y las fechas //(Modificar esto)
    @Query("SELECT sr.rol.nombrerol FROM SocioRolEntity sr WHERE sr.socio.numero_carnet = :numero_carnet AND (sr.fechafin IS NULL OR sr.fechafin > CURRENT_DATE)")
    String EncuentraRolActivoPorSocioId(@Param("numero_carnet") String numero_carnet);

    @Query("""
    SELECT sr.rol.nombrerol
    FROM SocioRolEntity sr
    WHERE sr.socio.numero_carnet = :numero_carnet
    AND (
            sr.rol.nombrerol = 'Presidente' 
         OR (sr.fechainicio <= CURRENT_DATE AND sr.fechafin >= CURRENT_DATE)
    )
""")
    String EncontrarRolParaLogin(String numero_carnet);



}
