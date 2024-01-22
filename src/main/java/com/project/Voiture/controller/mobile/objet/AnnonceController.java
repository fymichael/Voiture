package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.mobile.objet.Annonce;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceController {

    @GetMapping("/annonce/client/{idClient}")
    public Vector<Annonce> getClientAnnnonce(@PathVariable String idClient) throws Exception{
        Vector<Annonce> clientAnnonce = new Annonce().clientAnnonces(idClient, null);
        return clientAnnonce;

    }
    @PostMapping("/annonce")
    public void insert(@RequestBody Annonce newAnnonce) throws Exception {
        newAnnonce.insert(null);
    }
}
