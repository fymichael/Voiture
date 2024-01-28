package com.project.Voiture.controller.authentification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Voiture.securite.entite.VProfil;
import com.project.Voiture.securite.filter.JwtUtils;


@RestController
@RequestMapping("api/voiture")
public class AuthController {

    @PostMapping("/login")
    public void login(@RequestBody VProfil client, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            JwtUtils.createTokens(request, response, client.getUsername(), client.getMdp());
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
