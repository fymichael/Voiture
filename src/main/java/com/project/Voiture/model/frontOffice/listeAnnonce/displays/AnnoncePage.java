package com.project.Voiture.model.frontOffice.listeAnnonce.displays;

import com.project.Voiture.model.frontOffice.listeAnnonce.views.*;
import com.project.Voiture.model.backOffice.caracteristique.*;
import java.util.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class AnnoncePage {
    private List<VAnnonce> annonces;
    private DatasForFilter datasForFilter;
}
