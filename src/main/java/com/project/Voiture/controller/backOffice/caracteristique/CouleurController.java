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
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class CouleurController {

    @GetMapping("/couleurs")
    public Couleur[] getListe()throws Exception{
        Couleur c = new Couleur();
        Couleur[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/add-couleur")
    public void form(@RequestBody Couleur couleur)throws Exception{
       couleur.insert(null);
    }

    @PutMapping("/update-couleur")
    public void update(@RequestBody Couleur Couleur)throws Exception{
       Couleur.update(null);
    }

    @PutMapping("/delete-couleur")
    public void delete(@RequestBody Couleur Couleur)throws Exception{
       Couleur.delete(null);
    }
}
