package com.project.Voiture.securite.entite;

import lombok.*;
import java.time.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class VProfil {
    private String idProfil;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String mdp;
    private String contact;
    private Integer idRole;
    private String role;
    private String username;

}
