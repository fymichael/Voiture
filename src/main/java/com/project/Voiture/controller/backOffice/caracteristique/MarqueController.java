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
public class MarqueController {

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/marques")
    public Marque[] getListe()throws Exception{
        Marque c = new Marque();
        Marque[] liste=c.getAll(null);
        return liste;
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/add-marque")
    public void form(@RequestBody String nom)throws Exception{
       Marque c = new Marque();
       c.setIntitule(nom);
       c.insert(c.getIntitule(), null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/update-marque")
    public void update(@RequestBody Marque Marque)throws Exception{
       Marque.update(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/delete-marque")
    public void delete(@RequestBody Marque Marque)throws Exception{
       Marque.delete(null);
    }
}
