package com.project.Voiture.model.frontOffice.listeAnnonce.displays;

import com.project.Voiture.model.frontOffice.listeAnnonce.views.*;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class DatasForFilter {
    private Lieu [] lieux;
    private List<Categorie> categories;
    private Couleur [] couleurs;
    private Energie [] energies;
    private Marque [] marques;
    private ModeTransmission [] modeTransmission;
    private Specification [] specifications;
}
