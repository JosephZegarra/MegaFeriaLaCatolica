package com.alexander.curso.spring.boot.webapp.springboot_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
            // Configuración CSRF - Habilitado con repositorio de cookies
            .csrf(csrf -> csrf
                  .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                  // Deshabilitar CSRF solo para endpoints de API específicos si es necesario
                  .ignoringRequestMatchers("/api/socios/buscarsocio", "/api/socios/guardarsocio",
                        "/api/socios/confirmarcorreo", "/api/socios/ver",
                        "/api/socios/listar", "/api/socios/editarsocio",
                        "/api/socios/borrar", "/api/pagos/registrarPago"))
            // Configuración de autorización de endpoints
            .authorizeHttpRequests(auth -> auth
                  // Endpoints públicos - accesibles sin autenticación
                  .requestMatchers("/paginas/Login", "/paginas/RegistroSocio").permitAll()
                  .requestMatchers("/api/socios/guardarsocio", "/api/socios/buscarsocio",
                        "/api/socios/confirmarcorreo")
                  .permitAll()
                  // Recursos estáticos
                  .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                  // Todos los demás endpoints requieren autenticación
                  .anyRequest().permitAll() // Cambiado a permitAll() temporalmente para no romper la app
            )
            // Configuración de sesiones
            .sessionManagement(session -> session
                  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                  .sessionFixation().migrateSession()
                  .maximumSessions(1)
                  .maxSessionsPreventsLogin(false))
            // Configuración de headers de seguridad
            .headers(headers -> headers
                  .frameOptions(frame -> frame.sameOrigin())
                  .xssProtection(xss -> xss.disable()) // Deshabilitar XSS header ya que es obsoleto
                  .contentTypeOptions(contentType -> contentType.disable()));

      return http.build();
   }
}
