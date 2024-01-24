package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.objet.Voiture;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VoitureController {

    @PostMapping("/voiture")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void form(@RequestBody Voiture newVoiture) throws Exception {
        newVoiture.insert(null);
    }
    @PutMapping("/voiture/{idVoiture}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void update(@PathVariable String idVoiture, @RequestBody Voiture voiture) throws Exception {
        voiture.update(null, idVoiture);
    }
}
