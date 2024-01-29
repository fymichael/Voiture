package com.project.Voiture.securite.filter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Voiture.securite.entite.Login;
import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.repository.ProfilRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JwtUtils {
        static String SECRET = "mySecret1234";
        static long EXPIRE_TOKEN_ACCESS = 60 * 60 * 1000;
        static long EXPIRE_REFRESH_TOKEN =  120 * 60 * 1000;
        static String HEADER = "Authorization";
        static String PREFIX = "Bearer ";

        private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
    public void createTokens(HttpServletRequest request, HttpServletResponse response, String username, String mdp)
            throws Exception {
        try {
                if(username == null || mdp == null || username.trim().equals("") || mdp.trim().equals("")) {
                        throw new Exception("Veuillez saisir des valeurs corrects");
                }
                VProfil user = Login.authentificatedByUsernameOrEmail(username, null);
                if(user == null) {
                        throw new Exception("Erreur d'authentification : verifier votre email ou username");
                }
                System.out.println(" password 1 "+user.getMdp());
                System.out.println(" password 2 "+(mdp));
                
               if(!passwordEncoder.matches(mdp, user.getMdp())) {
                        throw new Exception("Erreur d'authentification : verifier votre mot de passe");
                }

                Algorithm algo1 = Algorithm.HMAC256(JwtUtils.SECRET);
                
                String jwtAccessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_TOKEN_ACCESS))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", "ROLE_"+user.getRole())
                        .sign(algo1);
        
                String jwtRefreshToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_REFRESH_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algo1);
        
                Map<String, String> idToken = new HashMap<>();
        
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwtRefreshToken);
                response.setHeader("message", "Authentification successfuly");
        System.out.println("Token = "+jwtAccessToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        } catch(Exception e) {
                e.printStackTrace();
                response.setHeader("error-message", e.getMessage());
        }       
    }

    public static void createRefreshTokens(HttpServletRequest request, HttpServletResponse response, String authToken)
            throws Exception {
         try {
                String jwt = authToken.substring(JwtUtils.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                VProfil vprofil = ProfilRepository.findByUsername(username, null);
                String jwtAccessToken = JWT.create()
                        .withSubject(vprofil.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtils.EXPIRE_TOKEN_ACCESS))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", "ROLE_"+vprofil.getRole())
                        .sign(algorithm);
                
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                    
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        } catch(Exception e) {
                response.setHeader("error-message", "Impossible de rafraichir votre token, veuilez reconnecter : "+e.getMessage());
        }
    }

    //Les urls ne necessite pas d'authentification
    public static List<String> getListUrls() {
            List<String> list = new ArrayList<>();
            list.add("/api/voiture/refreshToken/**");
            list.add("/api/voiture/inscription/**");
            list.add("/api/voiture/login/**");
            list.add("/api/voiture/allDiscussions/**");
            list.add("/api/voiture/accueil/**");
            list.add("/api/voiture/annonces/**");
            list.add("/api/voiture/annonce/**");
 	    list.add("/api/voiture/annonces/filter/**");
            list.add("/api/voiture/annonce/filter/**");
            list.add("/api/voiture/annonces/filter/prix/**");
            list.add("/api/voiture/annonces/filter/date/**");
            list.add("/api/voiture/annonces/filter/keyWord/**");
            list.add("/api/voiture/annonces/filter/multiCritere/**");

            return list;
    }

    //Est ce que le url est dans la liste
    public static boolean isUrlInList(String url) {
            List<String> list = JwtUtils.getListUrls();

            for(String item : list) {
                String valueFormatted = item.substring(0, item.length() - 3);
                if(valueFormatted.equals(url)) {
                        return true;
                }
            }

            return false;
    }

    //Avoir le profil grace au token
    public static VProfil getProfil(String authToken) throws Exception {
            VProfil profil = new VProfil();
            try {
                String jwt = authToken.substring(JwtUtils.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                profil = ProfilRepository.findByUsername(username, null);
                if(profil.getIdProfil() == null) {
                        throw new Exception("Erreur : imposible de resoudre le token");
                }
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }

            return profil;
    }
}