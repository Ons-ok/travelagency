package com.ditra.travelagency.core.chambre;

import com.ditra.travelagency.core.catégorie.Catégorie;
import com.ditra.travelagency.core.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChambreRespitory extends JpaRepository<Chambre, Integer> {
    Optional<Chambre> findByCatégorieAndType(Catégorie catégorie , Type type);
}
