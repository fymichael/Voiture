package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.backOffice.caracteristique.Specification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class SpecificationController {

    @GetMapping("/specifications")
    public Specification[] getListe()throws Exception{
        Specification c = new Specification();
        Specification[] liste=c.getAll(null);
        return liste;
    }

    @PostMapping("/specification")
    public void form(@RequestBody Specification modele)throws Exception{
       modele.insert(null);
    }

    @PutMapping("/specification")
    public void update(@RequestBody Specification Modele)throws Exception{
       Modele.update(null);
    }

    @DeleteMapping("/specification")
    public void delete(@RequestBody Specification Modele)throws Exception{
       Modele.delete(null);
    }
}
