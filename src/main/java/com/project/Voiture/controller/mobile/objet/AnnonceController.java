package com.project.Voiture.controller.mobile.objet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Voiture.model.frontOffice.listeAnnonce.views.VAnnonce;
import com.project.Voiture.model.mobile.objet.Annonce;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/voiture")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceController {

    @GetMapping("/annonce/non-valide")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public Vector<Annonce> getAnnonceNonValider() throws Exception {
        Vector<Annonce> clientAnnonce = new Annonce().AnnonceNonValider(null);
        return clientAnnonce;

    }

    @GetMapping("/annonce/detail/{idAnnonce}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public Annonce getAnnonceDetails(@PathVariable String idAnnonce) throws Exception {
        Annonce clientAnnonce = new Annonce().getById(idAnnonce, null);
        return clientAnnonce;

    }

    @GetMapping("/annonce/profil/{idClient}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public Vector<Annonce> getClientAnnonce(@PathVariable String idClient) throws Exception {
        Vector<Annonce> clientAnnonce = new Annonce().clientAnnonces(idClient, null);
        return clientAnnonce;

    }

    @PostMapping("/annonce")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void insert(@RequestBody Annonce newAnnonce) throws Exception {
        newAnnonce.insert(null);
    }

    @PostMapping("annonce/vente/{idAnnonce}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void vendre(@PathVariable String idAnnonce, @RequestBody Date dateVente) {
        try {
            new Annonce().vendre(null, idAnnonce, dateVente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("annonce/prix/{idAnnonce}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void changer_prix(@PathVariable String idAnnonce, @RequestBody Annonce annonce) throws Exception {
        annonce.changePrice(null, idAnnonce);
    }

    @DeleteMapping("annonce/prix/{idAnnonce}")
    @PostAuthorize("hasAuthority('ROLE_Client')")
    public void delete(@PathVariable String idAnnonce) throws Exception {
        new Annonce().delete(null, idAnnonce);
    }


    @GetMapping("annonce/validation/{idAnnonce}")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public void valider_annonce(@PathVariable String idAnnonce) throws Exception {
        Annonce annonce=new Annonce();
        annonce.setIdAnnonce(idAnnonce);
        annonce.validation(null);
    }

    @GetMapping("annonce/validation")
    @PostAuthorize("hasAuthority('ROLE_Administrateur')")
    public VAnnonce[] annonceValider() throws Exception {
        List<VAnnonce> liste=VAnnonce.getNonValider(null);
        return liste.toArray(new VAnnonce[liste.size()]);
    }
}
