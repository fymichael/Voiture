package com.project.Voiture.securite.filter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("succesfulAuthentication");

        User user = (User)authResult.getPrincipal();
        Algorithm algo1 = Algorithm.HMAC256(JwtUtils.SECRET);
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtils.EXPIRE_TOKEN_ACCESS))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algo1);

        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtils.EXPIRE_REFRESH_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo1);

        Map<String, String> idToken = new HashMap<>();

        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwtRefreshToken);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
    }
}
