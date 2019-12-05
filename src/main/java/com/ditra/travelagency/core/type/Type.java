package com.ditra.travelagency.core.type;

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
public class Type {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int id;

    private String type;
    private String description;
    @OneToMany (mappedBy = "type")
    private List<Chambre> chambres;


}

