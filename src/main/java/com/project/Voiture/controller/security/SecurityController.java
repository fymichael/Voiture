package com.project.Voiture.controller.security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.project.Voiture.securite.repository.ProfilRepository;
import com.project.Voiture.securite.entite.VProfil;


@RestController
@RequestMapping("api/voiture")
public class SecurityController {

   @GetMapping(path = "/refreshToken")
   public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken =request.getHeader("Authorization");
        if(authToken != null && authToken.startsWith("Bearer ")) {
            try {
                String jwt = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                VProfil vprofil = ProfilRepository.findByusername(username, null);
                System.out.println("User datn = "+vprofil.getDateNaissance());
                String jwtAccessToken = JWT.create()
                    .withSubject(vprofil.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", "ROLE_"+vprofil.getRole())
                    .sign(algorithm);
        
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
            
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch(Exception e) {
                throw e;
            }
      } else {
            throw new RuntimeException("Refresh token required");
      }
   }
}
