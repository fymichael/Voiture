package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Couleur;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
public class CouleurController {

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/couleurs")
    public Couleur[] getListe()throws Exception{
        Couleur c = new Couleur();
        Couleur[] liste=c.getAll(null);
        return liste;
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/add-couleur")
    public void form(@RequestBody String nom)throws Exception{
       Couleur c = new Couleur();
       c.setIntitule(nom);
       c.insert(c.getIntitule(), null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/update-couleur")
    public void update(@RequestBody Couleur Couleur)throws Exception{
       Couleur.update(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/delete-couleur")
    public void delete(@RequestBody Couleur Couleur)throws Exception{
       Couleur.delete(null);
    }
}
