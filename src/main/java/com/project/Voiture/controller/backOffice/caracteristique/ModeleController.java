package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Modele;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class ModeleController {

    @GetMapping("/models")
    public Modele[] getListe()throws Exception{
        Modele c = new Modele();
        Modele[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/add-modele")
    public void form(@RequestBody Modele modele)throws Exception{
       modele.insert(null);
    }

    @PutMapping("/update-modele")
    public void update(@RequestBody Modele Modele)throws Exception{
       Modele.update(null);
    }

    @PutMapping("/delete-modele")
    public void delete(@RequestBody Modele Modele)throws Exception{
       Modele.delete(null);
    }
}
