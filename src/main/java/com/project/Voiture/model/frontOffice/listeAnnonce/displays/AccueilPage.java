package com.project.Voiture.model.frontOffice.listeAnnonce.displays;

import com.project.Voiture.model.connection.Connect;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccueilPage {
    private List<Categorie> categories;
    private List<Marque> marques;


}
