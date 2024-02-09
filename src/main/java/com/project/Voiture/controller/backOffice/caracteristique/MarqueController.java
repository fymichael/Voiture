package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.project.Voiture.model.backOffice.caracteristique.Marque;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class MarqueController {

   @GetMapping("/marques")
   public Marque[] getListe()throws Exception{
      Marque c = new Marque();
      Marque[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/marque/{id}")
   public Marque getById(@PathVariable String id)throws Exception{
        Marque c = new Marque();
        c.setIdMarque(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/marque")
     public Marque form(@RequestBody Marque marque)throws Exception{
      return marque.insert(null);
    }

    @PutMapping("/marque")
     public void update(@RequestBody Marque marque)throws Exception{
       marque.update(null);
    }

    @DeleteMapping("/marque/{id}")
     public void delete(@PathVariable String id)throws Exception{
        Marque marque=new Marque();
        marque.setIdMarque(id);
        System.out.println(marque.getIdMarque());
        marque.delete(null);
    }

   @GetMapping("/marque")
   public Marque[] getPlusVendues()throws Exception{
      Marque c = new Marque();
      Marque[] marque=c.getMarquePusVendue(null);
      return marque;
   }

}
