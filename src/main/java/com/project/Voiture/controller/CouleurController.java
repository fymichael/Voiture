package com.project.Voiture.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.Voiture.model.Couleur;


@RestController
@RequestMapping("api/project")
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
    public void form(@RequestBody Couleur couleur)throws Exception{
       couleur.insert(null);
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
