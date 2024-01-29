package com.project.Voiture.model.frontOffice.historiquesFavoris.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "historique")
public class Historique {
    @Id
    private String id;
    private String idProfil;
    private String idAnnonce;
    private String dateCreation;
    private String type;
}
