package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Energie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class EnergieController {

    @GetMapping("/energies")
    public Energie[] getListe()throws Exception{
        Energie c = new Energie();
        Energie[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/add-energie")
    public void form(@RequestBody String nom)throws Exception{
       Energie c = new Energie();
       c.setIntitule(nom);
       c.insert(c.getIntitule(), null);
    }

    @PutMapping("/update-energie")
    public void update(@RequestBody Energie Energie)throws Exception{
       Energie.update(null);
    }

    @PutMapping("/delete-energie")
    public void delete(@RequestBody Energie Energie)throws Exception{
       Energie.delete(null);
    }
}
