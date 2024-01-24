package com.project.Voiture.controller.mobile.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.securite.entite.Profil;

import org.springframework.web.bind.annotation.RequestMapping;
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
    public Profil login(@RequestBody Profil newClient) throws Exception {
        return newClient.login(null);
    }

}
