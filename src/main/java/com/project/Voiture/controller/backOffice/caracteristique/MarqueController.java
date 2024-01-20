package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Marque;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class MarqueController {

    @GetMapping("/marques")
    public Marque[] getListe()throws Exception{
        Marque c = new Marque();
        Marque[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/add-marque")
    public void form(@RequestBody String nom)throws Exception{
       Marque c = new Marque();
       c.setIntitule(nom);
       c.insert(c.getIntitule(), null);
    }

    @PutMapping("/update-marque")
    public void update(@RequestBody Marque Marque)throws Exception{
       Marque.update(null);
    }

    @PutMapping("/delete-marque")
    public void delete(@RequestBody Marque Marque)throws Exception{
       Marque.delete(null);
    }
}
