package com.alexander.curso.spring.boot.webapp.springboot_web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil
{
    private final String secret = "MiClaveSecretaMuyLargaParaJWT1234567890";
    private final long expiration=1000*60*60*24;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //Generar token JWT
    public String generateToken(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    //Validar Token
    public boolean validateToken(String token)
    {
        try{
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch(JwtException e)
        {
            return false;
        }
    }

    //obtener el username del token
    public String getUsernameFromToken(String token)
    {
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return  claims.getSubject();
    }
}
