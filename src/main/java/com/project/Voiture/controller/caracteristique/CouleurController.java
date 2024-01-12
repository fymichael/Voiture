package com.project.Voiture.controller.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.caracteristique.Couleur;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/project")
public class CouleurController {

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/liste-couleur")
    public Couleur[] getListe()throws Exception{
        Couleur c = new Couleur();
        Couleur[] liste=c.getAll(null);
        return liste;
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/form-couleur")
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
