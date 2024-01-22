package com.project.Voiture.securite.entite;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;
import java.time.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class Profil {
    private Integer idProfil;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String mdp;
    private String ccontact;
    private Integer idRole;
}
