package com.project.Voiture.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Voiture.model.JwtUtil;
import com.project.Voiture.model.Admin;


@RestController
@RequestMapping("api/project")
public class AuthController {

    @Autowired
    private JwtUtil util;

    @PostMapping("/login")
    public String login(@RequestBody Admin auth) throws Exception{
        Admin a = new Admin();
        a.setEmail(auth.getEmail());
        a.setMdp(auth.getMdp());
        Admin utilisateur=a.getUtilisateur(null);
        if (a==null) {
            throw new Exception("Nom d'utilisateur ou mot de passe invalide !");
        }
        String token = util.creationToken(a);
        return token;
    }
}
