package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.service.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoServiceImpl implements CorreoService
{
    @Autowired
    private JavaMailSender mailSender;


     @Override
    public void enviarCorreo(String para,String asunto, String mensaje) {
         if(para==null || para.trim().isEmpty())
         {
             System.out.println("No se envio el correo porque, el destinatario es nulo o vacio");

         }
         try {
             SimpleMailMessage mail=new SimpleMailMessage();
             mail.setTo(para);
             mail.setSubject(asunto);
             mail.setText(mensaje);
             mailSender.send(mail);
             System.out.println("Correo enviado exitosamente para "+ para);

         } catch (MailException e) {
             System.out.println("Error al enviar correo a"+para+":"+e.getMessage());
             e.printStackTrace(); // Aquí verás el error real de envío
         }
     }
}
