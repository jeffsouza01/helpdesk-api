package com.porto.helpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
