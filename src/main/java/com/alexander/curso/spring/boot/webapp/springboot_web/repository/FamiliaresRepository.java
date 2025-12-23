package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.FamiliaresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaresRepository extends JpaRepository<FamiliaresEntity, Integer> {
    boolean existsByDni(String dni);
    boolean existsBySocio_Idsocio(Integer idsocio);
}
