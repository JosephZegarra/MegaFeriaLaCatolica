package com.alexander.curso.spring.boot.webapp.springboot_web;

import com.alexander.curso.spring.boot.webapp.springboot_web.controller.SocioViewController;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.AsistenciaService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SocioViewController.class)
public class SocioViewControllerTest
{
   @Autowired
    private MockMvc mockMvc;

   @MockitoBean
    private SocioService socioService;

   @MockitoBean
    private SocioRolRepository socioRolRepository;

   @MockitoBean
   private AsistenciaService asistenciaService;

   @MockitoBean
    private JwtUtil jwtUtil;

   //Este Test imita que un usuario que no existe intenta ingresar al sistema, verificar si redirige al Login
   @Test
    void testSocioNoExiste() throws Exception {
       //Simula que el socio no existe
       Mockito.when(socioService.validarSocio("CARNET-100", "123")).thenReturn(null);

       mockMvc.perform(post("/socios/buscarsocio")
                       .param("numero_carnet", "CARNET-100")
                       .param("contrasena", "123")
                       .param("rol", "Secretaria")
               ).andExpect(status().isOk())
               .andExpect(view().name("Login"))
               .andExpect(model().attributeExists("error"));



   }

   //Si el socio esta inactivo y quiere ingresaer al sistema  debe ir a error

   @Test
   void testSocioInactivo() throws Exception{
       SocioDTO socio=new SocioDTO();
       socio.setEstado("Inactivo");

       Mockito.when(socioService.validarSocio("C001","123")).thenReturn(socio);

       mockMvc.perform(post("/socios/buscarsocio")
               .param("numero_carnet","C001")
               .param("contrasena","123")
               .param("rol","Socio"))
               .andExpect(status().isOk())
               .andExpect(view().name("Login"))
               .andExpect(model().attribute("error", "El socio ya no pertenece a la asociación"));
   }

  //Si el socio el socio esta activo y no tiene un rol y quiere ingresar a un rol que no le pertenece
    @Test
    void testSocioActivoSinRolIntentandoEntrarSecretaria() throws Exception {

        MockHttpSession session = new MockHttpSession();

        SocioDTO socio = new SocioDTO();
        socio.setNumero_carnet("CARNET-001");
        socio.setEstado("Activo");

        // socio existe
        Mockito.when(socioService.validarSocio("CARNET-001", "123"))
                .thenReturn(socio);

        // no tiene rol activo
        Mockito.when(socioRolRepository.EncuentraRolActivoPorSocioId("CARNET-001"))
                .thenReturn(null);

        mockMvc.perform(post("/socios/buscarsocio")
                        .param("numero_carnet", "CARNET-001")
                        .param("contrasena", "123")
                        .param("rol", "Secretaria") // intenta entrar a un cargo
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error",
                        "No tiene un cargo asignado, solo puede ingresar como Socio.")
                );
    }


    //Si el socio que tiene un rol asignado selecciona un rol incorrecto
    @Test
    void testSociocoRolSeleccionaRolIncorrecto() throws Exception{
       SocioDTO socio=new SocioDTO();
       socio.setEstado("Activo");

       Mockito.when(socioService.validarSocio("C001","123")).thenReturn(socio);

       //Aca le digo que su rol es presidente
       Mockito.when(socioRolRepository.EncuentraRolActivoPorSocioId("C001")).thenReturn("Presidente");

       //Aca son los parametros que pone en el login puso que su rol es Secretaria
        mockMvc.perform(post("/socios/buscarsocio")
                        .param("numero_carnet", "C001")
                        .param("contrasena", "123")
                        .param("rol", "Secretaria"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(model().attribute("error", "El rol seleccionado no coincide con el rol registrado en la asociación."));

    }

    //Sie l socio con rol presidente selecciona correctamente su rol en el combobox
    @Test
    void testSocioPresidenteAccesoCorrecto() throws Exception {
       SocioDTO socioDTO=new SocioDTO();
        socioDTO.setNumero_carnet("C001");
        socioDTO.setEstado("Activo");

        Mockito.when(socioService.validarSocio("C001","123")).thenReturn(socioDTO);
        Mockito.when(socioRolRepository.EncuentraRolActivoPorSocioId("C001"))
                .thenReturn("Presidente");

        Mockito.when(jwtUtil.generateToken("C001"))
                .thenReturn("TOKEN_FAKE");


        mockMvc.perform(post("/socios/buscarsocio")
                        .param("numero_carnet", "C001")
                        .param("contrasena", "123")
                        .param("rol", "Presidente"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/paginas/menupresidente"));

    }

    //Sie l socio con rol tesorera esta activo   y selecciona su rol correctamente
    @Test
    void testSocioTesoreraAccesoCorrecto() throws Exception {

        SocioDTO socio = new SocioDTO();
        socio.setNumero_carnet("C001");
        socio.setEstado("Activo");

        Mockito.when(socioService.validarSocio("C001", "123"))
                .thenReturn(socio);

        Mockito.when(socioRolRepository.EncuentraRolActivoPorSocioId("C001"))
                .thenReturn("Tesorera");

        Mockito.when(jwtUtil.generateToken("C001"))
                .thenReturn("TOKEN_FAKE");

        mockMvc.perform(post("/socios/buscarsocio")
                        .param("numero_carnet", "C001")
                        .param("contrasena", "123")
                        .param("rol", "Tesorera"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/paginas/tesorera"));
    }



}
