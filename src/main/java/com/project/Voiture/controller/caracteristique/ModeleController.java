package com.project.Voiture.controller.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.caracteristique.Modele;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/project")
public class ModeleController {

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/liste-modele")
    public Modele[] getListe()throws Exception{
        Modele c = new Modele();
        Modele[] liste=c.getAll(null);
        return liste;
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/form-modele")
    public void form(@RequestBody Modele modele)throws Exception{
       modele.insert(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/update-modele")
    public void update(@RequestBody Modele Modele)throws Exception{
       Modele.update(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/delete-modele")
    public void delete(@RequestBody Modele Modele)throws Exception{
       Modele.delete(null);
    }
}
