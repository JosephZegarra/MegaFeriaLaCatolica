package com.alexander.curso.spring.boot.webapp.springboot_web.service;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.DashboardDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface DashboardService {
    //public DashboardDTO obtenerEstadisticasDashboard();
    Map<String, Object> getDashboardData();
}
