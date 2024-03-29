package com.project.Voiture.controller.backOffice.caracteristique;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.project.Voiture.model.backOffice.caracteristique.Specification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("api/voiture")
public class SpecificationController {

   @GetMapping("/specifications")
      public Specification[] getListe()throws Exception{
      Specification c = new Specification();
      Specification[] liste=c.getAll(null);
      return liste;
   }

   @GetMapping("/specification/{id}")
      public Specification getById(@PathVariable String id)throws Exception{
        Specification c = new Specification();
        c.setIdSpecification(id);
        c=c.getById(null);
        return c;
    }

    @PostMapping("/specification")
        public Specification form(@RequestBody Specification specification)throws Exception{
      return specification.insert(null);
    }

    @PutMapping("/specification")
        public void update(@RequestBody Specification specification)throws Exception{
       specification.update(null);
    }

    @DeleteMapping("/specification/{id}")
        public void delete(@PathVariable String id)throws Exception{
        Specification specification=new Specification();
        specification.setIdSpecification(id);
        System.out.println(specification.getIdSpecification());
        specification.delete(null);
    }

}
