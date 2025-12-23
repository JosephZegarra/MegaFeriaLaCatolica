package com.alexander.curso.spring.boot.webapp.springboot_web.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Gestiona los errores de la aplicación
@RestControllerAdvice
public class GlobalExeptionHandler
{
    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<String> handleSocioNotFound(NotFoundExeption ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex)
    {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
