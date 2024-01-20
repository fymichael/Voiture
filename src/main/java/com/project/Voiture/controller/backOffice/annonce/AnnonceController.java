package com.project.Voiture.controller.backOffice.annonce;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.statistique.Annonce;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("api/voiture")
public class AnnonceController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/annonce")
    public void form(@RequestBody Annonce newAnnonce) throws Exception {
        newAnnonce.insert(null);
        /*
        JSON attendus
         * {
         * "voiture": {
         * "idVoiture" : "CAR0001",
         * "marque": {
         * "idMarque": "MRQ0001",
         * "intitule": "Toyota",
         * "etat": 1
         * },
         * "categorie": {
         * "idCategorie": "CTG0001",
         * "intitule": "4x4",
         * "etat": 1
         * },
         * "modele": {
         * "idModele": "MDL0001",
         * "intitule": "Fortuner",
         * "etat": 1
         * },
         * "energie": {
         * "idEnergie": "ENG0001",
         * "intitule": "Diesel",
         * "etat": 1
         * },
         * "couleur": {
         * "idCouleur": "CLR0003",
         * "intitule": "Vert",
         * "etat": 1
         * },
         * "anneeSortie": "2020",
         * "immatriculation": "2020TBR",
         * "autonomie": 15,
         * "modeTransmission": {
         * "idModeTransmission": "MTR0001",
         * "intitule": "Manuel",
         * "etat": 1
         * }
         * },
         * "description":
         * "Une voiture japonaise de luxe 4 vitres électriques et d'une élégance incomparable"
         * ,
         * "date": "2024-01-20T12:00:00Z",
         * "prix": 255000000,
         * "idClient": "CLT0001",
         * "status": 1
         * }
         * 
         */
    }
}
