package com.project.Voiture.model.frontOffice.listeAnnonce.displays;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class FilterObject {
    private String modele;
    private String lieu;
    private String categorie;
    private String marque;
    private String energie;
    private String modeTransmission;
    private String couleur;
    private String specification;
    private String anneeMin;
    private String anneeMax;
    private String prixMin;
    private String prixMax;
    private String dateMin;
    private String dateMax; 

}
