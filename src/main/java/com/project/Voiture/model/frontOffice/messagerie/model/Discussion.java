package com.project.Voiture.model.frontOffice.messagerie.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.Voiture.securite.entite.Profil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "discussion")
public class Discussion {
    @Id
    private String id;
    private List<Profil> membres;
    private List<Message> messages;
    private String dateCreation;  
    private Integer status;  
}
