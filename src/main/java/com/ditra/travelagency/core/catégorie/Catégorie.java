package com.ditra.travelagency.core.catégorie;

import com.ditra.travelagency.core.chambre.Chambre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Catégorie {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id;
    private String categorie;
    private String description;
    @OneToMany (mappedBy = "catégorie")
    private List<Chambre> chambres;


}
