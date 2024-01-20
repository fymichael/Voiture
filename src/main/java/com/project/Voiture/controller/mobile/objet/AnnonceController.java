package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.objet.Annonce;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceController {

    @PostMapping("/annonce")
    public void form(@RequestBody Annonce newAnnonce) throws Exception {
        newAnnonce.insert(null);
        /*
         *format JSON attendus
         * {
         * "voiture": {
         * "idVoiture" : "CAR0002",
         * "idMarque": "MRQ0001",
         * "idCategorie": "CTG0001",
         * "idModele": "MDL0001",
         * "idEnergie": "ENG0001",
         * "idCouleur": "CLR0003",
         * "anneeSortie": "2020",
         * "immatriculation": "2020TBR",
         * "autonomie": 15,
         * "idModeTransmission": "MTR0001"
         * },
         * "description":
         * "Une voiture japonaise de luxe 4 vitres électriques et d'une élégance incomparable"
         * ,
         * "prix": 255000000,
         * "idClient": "CLT0001",
         * "status": 1
         * }
         * 
         * 
         */
    }
}
