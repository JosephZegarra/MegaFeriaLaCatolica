package com.alexander.curso.spring.boot.webapp.springboot_web.exeption;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message) {
        super(message);
    }
}
