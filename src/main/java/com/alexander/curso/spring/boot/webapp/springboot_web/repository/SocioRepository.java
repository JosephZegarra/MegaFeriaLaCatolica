package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SocioRepository extends JpaRepository<SocioEntity, Integer> {


    @Transactional
    @Modifying
    @Query ("UPDATE SocioEntity s SET s.estado = 'Inactivo' WHERE s.idsocio = :id")
    void softDelete(@Param("id") Integer id);

    List<SocioEntity> findByEstado(String estado);
    //Optional<SocioEntity> findByNumeroCarnetAndContrasena(String numero_carnet, String contrasena);
    //@Query("SELECT s FROM SocioEntity s WHERE s.numero_carnet = :numero_carnet AND s.contrasena = :contrasena")
    //Optional<SocioEntity> buscarPorCarnetYContrasena(@Param("numero_carnet") String numero_carnet,
                                                  //   @Param("contrasena") String contrasena);

    @Query("SELECT s.idsocio FROM SocioEntity s WHERE s.numero_carnet = :numero_carnet")
    Optional<Integer> obtenerIdPorNumeroCarnet(@Param("numero_carnet") String numero_carnet);

    boolean existsByDni(String dni);

    Optional<SocioEntity> findByToken(String token);//Verifica el enlace de confirmación

    @Query("SELECT s FROM SocioEntity s WHERE s.numero_carnet = :numero_carnet")
    Optional<SocioEntity> findByNumeroCarnet(@Param("numero_carnet")String numero_carnet);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM SocioEntity s WHERE s.numero_carnet = :numero_carnet")
    boolean existsByNumero_carnet(String numero_carnet);

    @Query(value = "SELECT numero_carnet FROM socios ORDER BY numero_carnet DESC LIMIT 1", nativeQuery = true)
    String findUltimoNumeroCarnet();

    //Nuevo codigoo
    long countByEstado(String estado);
}
