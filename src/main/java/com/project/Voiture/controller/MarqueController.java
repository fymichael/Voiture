package com.project.Voiture.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.Voiture.model.Marque;


@RestController
@RequestMapping("api/project")
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
