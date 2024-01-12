package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Categorie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("api/voiture")
public class CategorieController {

   @CrossOrigin(origins = "*", allowedHeaders ="*")
   @GetMapping("/categories")
   public Categorie[] getListe()throws Exception{
      Categorie c = new Categorie();
      Categorie[] liste=c.getAll(null);
      return liste;
   }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping("/add-categorie")
    public void form(@RequestBody Categorie c)throws Exception{
       c.insert(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/update-categorie")
    public void update(@RequestBody Categorie categorie)throws Exception{
       categorie.update(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("/delete-categorie")
    public void delete(@RequestBody Categorie categorie)throws Exception{
       categorie.delete(null);
    }
}
