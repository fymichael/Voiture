package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.objet.Voiture;
import com.project.Voiture.model.backOffice.statistique.Statistique;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatistiqueController {

    @GetMapping("/stat")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Statistique statistique() throws Exception {
      return new Statistique().getStatistique(null);
    }
}
