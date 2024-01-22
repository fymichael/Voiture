package com.project.Voiture.securite.entite;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    private Integer idRole;
    private String role;
}
