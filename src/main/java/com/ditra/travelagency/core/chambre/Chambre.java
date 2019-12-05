package com.ditra.travelagency.core.chambre;

import com.ditra.travelagency.core.catégorie.Catégorie;
import com.ditra.travelagency.core.hotel.Hotel;
import com.ditra.travelagency.core.type.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Type type;
    @ManyToOne
    private Catégorie catégorie;

    @ManyToMany(mappedBy = "chambres")
    private List<Hotel>hotels;

}
