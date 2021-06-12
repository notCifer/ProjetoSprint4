package com.projeto.loja.services;

import java.util.Date;

import com.projeto.loja.models.Login;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private String secret = "pjugk?xNT7^xg3yT5A@un8&Yy=pNhBUdDP69WYegCqD6KUMSXhHLQd+&+u28AJkL@VA6e=!CUC-x9vURG7nN=N97ApGt3$-@NtpLVak%R88PjZ-=pq@EwHU*Z554=Cjg$&vP#RSPzC8=XkNtRZ8YxZed+Ycbq53!Q?C8%sAcawZkqg4?HNzpjw?W?VDE6c#dBU#NnQ5v$p?sA2cLuj4YhF5CQ+WcbZVQ#LWu*UzcF!dpUFMRnAmqXbrhqRHsfQVv";
    private String expiration = "86400000";

    public String gerarToken(Authentication auth){
        Login login = (Login) auth.getPrincipal();
        Date hoje = new Date();
        Date DataExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
        .setIssuer("API SPRINT4")
        .setSubject(login.getId().toString())
        .setIssuedAt(hoje)
        .setExpiration(DataExpiration)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    
}