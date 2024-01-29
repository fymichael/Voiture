package com.project.Voiture.controller.mobile.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.securite.entite.Profil;
import com.project.Voiture.securite.repository.ProfilRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfilController {
    @Autowired
    private ProfilRepository profilRepository;

    @PostMapping("/inscription")
    public void form(@RequestBody Profil newClient) throws Exception {
        profilRepository.insert(newClient, null);
    }
}
