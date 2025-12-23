package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.DeudaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeudaRepository extends JpaRepository<DeudaEntity,Integer>
{
    Optional<DeudaEntity>findById(Integer iddeuda);


    //Mira estoy diciendo que me deuvelta la listta de deudas de la base de datos
    //pero que acceda a la tabla socioasistencia y que verifica si el idsocio que le envio
    //este busca en esa tabla socioasistencia todos los que coincidan con idsocio que le envie
    //va a la tabla deuda y todos esos idasistencia que coinciden extrae una lista

    @Query("SELECT d FROM DeudaEntity d WHERE d.socioAsistenciaEntity.socio.idsocio=:idsocio")
    List<DeudaEntity> findBySocioIdsocio(@Param("idsocio")Integer idsocio);
}
