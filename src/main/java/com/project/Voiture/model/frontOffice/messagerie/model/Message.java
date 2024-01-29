package com.project.Voiture.model.frontOffice.messagerie.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.Voiture.securite.entite.Profil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Message {
    private String dateCreation;
    private String emetteur;  
    private String contenu;
    private Integer status;  
}