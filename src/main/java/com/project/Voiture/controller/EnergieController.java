package com.project.Voiture.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.Voiture.model.Energie;


@RestController
@RequestMapping("api/project")
public class EnergieController {

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("/energies")
    public Energie[] getListe()throws Exception{
        Energie c = new Energie();
        Energie[] liste=c.getAll(null);
        return liste;
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/add-energie")
    public void form(@RequestBody String nom)throws Exception{
       Energie c = new Energie();
       c.setIntitule(nom);
       c.insert(c.getIntitule(), null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/update-energie")
    public void update(@RequestBody Energie Energie)throws Exception{
       Energie.update(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/delete-energie")
    public void delete(@RequestBody Energie Energie)throws Exception{
       Energie.delete(null);
    }
}
