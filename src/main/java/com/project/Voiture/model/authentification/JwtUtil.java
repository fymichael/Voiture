package com.project.Voiture.model.authentification;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import java.util.Date;

@Component
public class JwtUtil {
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    
    private final String SECRET_KEY = "secret";
    private final long ACCESS_TOKEN_VALIDITY = 60*60*1000;  // une heure
    
    private final JwtParser jwtParser;
    
    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }
    
    public String creationToken(Admin a) throws Exception {
        Claims claims = Jwts.claims().setSubject(String.valueOf(a.getId()));
        claims.put("admin", a);
        
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + ACCESS_TOKEN_VALIDITY);
        
        String token =  Jwts.builder()
                                .setClaims(claims)
                                .setExpiration(tokenValidity)
                                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                                .compact();
        return token;
    }  
}
