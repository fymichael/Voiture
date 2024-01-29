package com.project.Voiture.model.frontOffice.historiquesFavoris.displays;

import java.util.List;

import com.project.Voiture.model.frontOffice.historiquesFavoris.model.Historique;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class HistoriqueAnnonce {
    List<Historique> historiqueAnnonces;
}
