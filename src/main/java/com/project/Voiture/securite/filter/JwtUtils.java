package com.project.Voiture.securite.filter;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.repository.ProfilRepository;

public class JwtUtils {
    private static String secret = "mySecret1234";

    public static String createTokens(HttpServletRequest request, HttpServletResponse response, String username, String mdp)
            throws Exception {
        System.out.println("succesfulAuthentication");

        VProfil user = ProfilRepository.findByUsername(username, null);
        System.out.println(user.getUsername());
        System.out.println(user.getRole());
        Algorithm algo1 = Algorithm.HMAC256(secret);
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", "ROLE_"+user.getRole())
                .sign(algo1);

        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo1);

        Map<String, String> idToken = new HashMap<>();

        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwtRefreshToken);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        return jwtAccessToken;

    }
}
