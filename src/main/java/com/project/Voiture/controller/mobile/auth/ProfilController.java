package com.project.Voiture.controller.mobile.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.securite.entite.Profil;
import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfilController {

    @PostMapping("/inscription")
    public void form(@RequestBody Profil newClient) throws Exception {
        newClient.insert(null);
    }

    @PostMapping("/login")
    public String login(@RequestBody VProfil client, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return JwtUtils.createTokens(request, response, client.getUsername(), client.getMdp());
    }

}
