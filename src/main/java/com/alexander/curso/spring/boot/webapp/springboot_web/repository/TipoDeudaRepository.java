package com.alexander.curso.spring.boot.webapp.springboot_web.repository;

import com.alexander.curso.spring.boot.webapp.springboot_web.entity.TipoDeudaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoDeudaRepository extends JpaRepository<TipoDeudaEntity,Long> {
   Optional<TipoDeudaEntity> findByNombre(String nombre);
}
