package com.ditra.travelagency.core.hotel;

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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String nom ;
    private String description;
    private Integer etoile;
    private String adresse;
    private Integer telephone;
    @ManyToMany
    private List<Chambre> chambres;


    public void addChambre(Chambre chambre) {
        chambres.add(chambre);
    }
    public void deleteChambre (int i){
        chambres.remove(chambres.get(i));
    }
}
